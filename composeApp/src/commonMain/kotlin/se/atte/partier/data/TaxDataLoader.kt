package se.atte.partier.data

import kotlinx.serialization.json.Json
import partier.composeapp.generated.resources.Res

object TaxDataLoader {
    private var taxData: TaxData? = null
    
    suspend fun loadTaxData(): TaxData {
        if (taxData == null) {
            val jsonBytes = Res.readBytes("files/tax_data.json")
            val jsonContent = jsonBytes.decodeToString()
            val json = Json { ignoreUnknownKeys = true }
            taxData = json.decodeFromString<TaxData>(jsonContent)
        }
        return taxData!!
    }
    
    fun calculatePartyTaxes(grossSalary: Double, taxData: TaxData): List<PartyTaxCalculation> {
        return taxData.parties.map { party ->
            val totalTaxRate = party.taxProposals.incomeTax.totalTaxRate
            val jobbskatteavdrag = party.taxProposals.deductions.jobbskatteavdrag
            val grundavdrag = party.taxProposals.deductions.grundavdrag
            
            // Swedish tax calculation:
            // 1. Apply grundavdrag (basic deduction) to taxable income
            val taxableIncome = maxOf(0.0, grossSalary - grundavdrag)
            
            // 2. Calculate tax on taxable income
            val taxOnTaxableIncome = taxableIncome * totalTaxRate
            
            // 3. Apply jobbskatteavdrag (employment tax deduction) - this reduces the tax
            val jobbskatteavdragAmount = grossSalary * jobbskatteavdrag
            val finalTaxAmount = maxOf(0.0, taxOnTaxableIncome - jobbskatteavdragAmount)
            
            // 4. Calculate net salary
            val netSalary = grossSalary - finalTaxAmount
            
            // 5. Calculate effective tax rate
            val effectiveTaxRate = if (grossSalary > 0) finalTaxAmount / grossSalary else 0.0
            
            PartyTaxCalculation(
                partyCode = party.partyCode,
                partyName = party.partyName,
                grossSalary = grossSalary,
                totalTaxRate = totalTaxRate,
                jobbskatteavdrag = jobbskatteavdrag,
                grundavdrag = grundavdrag,
                netSalary = netSalary,
                taxAmount = finalTaxAmount,
                effectiveTaxRate = effectiveTaxRate
            )
        }.sortedByDescending { it.netSalary } // Sort by highest net salary first
    }
}
