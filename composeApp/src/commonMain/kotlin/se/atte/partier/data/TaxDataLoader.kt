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
    
    fun calculatePartyTaxes(grossSalary: Double, taxData: TaxData, age: Int = 30): List<PartyTaxCalculation> {
        return taxData.parties.map { party ->
            // Use actual party tax proposals from JSON data
            val totalTaxRate = party.taxProposals.incomeTax.totalTaxRate
            val highIncomeThreshold = party.taxProposals.incomeTax.highIncomeThreshold
            val highIncomeTaxRate = party.taxProposals.incomeTax.highIncomeTaxRate
            
            // Additional taxes from party proposals (only those in real payslip)
            val begravningsavgift = party.taxProposals.additionalTaxes.begravningsavgift
            val publicServiceAvgift = party.taxProposals.additionalTaxes.publicServiceAvgift
            
            // Swedish tax calculation - Step by step according to official rules:
            
            // Step 1: Grundavdrag (varies based on income level and age)
            // Under 66 år: Upp till 8,750 kr/månad (17,300 kr/år)
            // 66+ år: Upp till 12,000 kr/månad (107,400 kr/år)
            val dynamicGrundavdrag = calculateGrundavdrag(grossSalary, age)
            val taxableIncome = maxOf(0.0, grossSalary - dynamicGrundavdrag)
            
            // Step 2: Kommunalskatt (beräknas på den beskattningsbara inkomsten)
            // Varje parti kan ha olika satser (t.ex. Vänsterpartiet 35%, Liberalerna 30%)
            val kommunalTax = taxableIncome * totalTaxRate
            
            // Step 3: Statlig skatt (läggs till endast om inkomsten överstiger brytpunkten)
            // Under 66 år: 643,100 kr/år, 66+ år: 733,200 kr/år
            // 20% på den del som överstiger brytpunkten
            val effectiveHighIncomeThreshold = if (age >= 66) {
                733200.0 // 66+ år
            } else {
                643100.0 // Under 66 år
            }
            
            val statligTax = if (grossSalary > effectiveHighIncomeThreshold) {
                (grossSalary - effectiveHighIncomeThreshold) * 0.20 // 20% statlig skatt
            } else {
                0.0
            }
            
            // Step 4: Jobbskatteavdrag (2025-regler: Upp till 3,941 kr/månad)
            // Börjar vid ca 3,000 kr och höjs tills 40,000 kr brutto, sedan har taket uppnåts
            val dynamicJobbskatteavdrag = calculateJobbskatteavdrag(grossSalary, age)
            val incomeTaxAfterDeduction = maxOf(0.0, (kommunalTax + statligTax) - dynamicJobbskatteavdrag)
            
            // Step 5: Ytterligare skatter (endast de som finns i lönebeskedet)
            // Begravningsavgift och public service-avgift
            val begravningTax = grossSalary * begravningsavgift
            val publicServiceTax = grossSalary * publicServiceAvgift
            
            // Step 6: Skattereduktion förvärvsinkomst (från lönebeskedet: +125 kr)
            val forvarvsinkomstReduktion = 125.0
            
            // Step 7: Nettolön (bruttolön - total skatt + reduktioner = nettolön)
            val finalTaxAmount = incomeTaxAfterDeduction + begravningTax + publicServiceTax - forvarvsinkomstReduktion
            
            // 6. Calculate net salary
            val netSalary = grossSalary - finalTaxAmount
            
            // 7. Calculate final effective tax rate
            val finalEffectiveTaxRate = if (grossSalary > 0) finalTaxAmount / grossSalary else 0.0
            
            PartyTaxCalculation(
                partyCode = party.partyCode,
                partyName = party.partyName,
                grossSalary = grossSalary,
                totalTaxRate = totalTaxRate,
                jobbskatteavdrag = dynamicJobbskatteavdrag,
                grundavdrag = dynamicGrundavdrag.toInt(),
                netSalary = netSalary,
                taxAmount = finalTaxAmount,
                effectiveTaxRate = finalEffectiveTaxRate
            )
        }.sortedByDescending { it.netSalary } // Sort by highest net salary first
    }
    
    private fun calculateGrundavdrag(annualIncome: Double, age: Int): Double {
        // Grundavdrag calculation based on Swedish tax rules
        // Under 66 år: Upp till 8,750 kr/månad (17,300 kr/år)
        // 66+ år: Upp till 12,000 kr/månad (107,400 kr/år)
        val monthlyIncome = annualIncome / 12

        return when {
            age >= 66 -> {
                // 66+ år: Upp till 12,000 kr/månad (107,400 kr/år)
                when {
                    monthlyIncome < 5000.0 -> monthlyIncome * 0.6
                    monthlyIncome < 15000.0 -> 3000.0 + (monthlyIncome - 5000.0) * 0.4
                    monthlyIncome < 30000.0 -> 7000.0 + (monthlyIncome - 15000.0) * 0.2
                    monthlyIncome < 50000.0 -> 10000.0 + (monthlyIncome - 30000.0) * 0.1
                    else -> 12000.0 // Cap at 12,000 kr/month for 66+
                }
            }
            else -> {
                // Under 66 år: Upp till 8,750 kr/månad (17,300 kr/år)
                when {
                    monthlyIncome < 5000.0 -> monthlyIncome * 0.5
                    monthlyIncome < 15000.0 -> 2500.0 + (monthlyIncome - 5000.0) * 0.3
                    monthlyIncome < 30000.0 -> 5500.0 + (monthlyIncome - 15000.0) * 0.15
                    monthlyIncome < 50000.0 -> 7750.0 + (monthlyIncome - 30000.0) * 0.05
                    else -> 8750.0 // Cap at 8,750 kr/month for under 66
                }
            }
        }
    }
    
    private fun calculateJobbskatteavdrag(annualIncome: Double, age: Int): Double {
        // Jobbskatteavdrag 2025: Up to 3,941 kr/month
        // Starts at ca 3,000 kr and increases until 40,000 kr gross, then capped
        val monthlyIncome = annualIncome / 12

        return when {
            age >= 66 -> {
                // Higher jobbskatteavdrag for 66+ (up to 4,200 kr/month)
                when {
                    monthlyIncome < 3000.0 -> 0.0
                    monthlyIncome < 10000.0 -> (monthlyIncome - 3000.0) * 0.15
                    monthlyIncome < 20000.0 -> 1050.0 + (monthlyIncome - 10000.0) * 0.12
                    monthlyIncome < 40000.0 -> 2250.0 + (monthlyIncome - 20000.0) * 0.08
                    else -> 3850.0 // Cap at 3,850 kr for 66+
                }
            }
            else -> {
                // Standard jobbskatteavdrag for under 66 (up to 3,941 kr/month)
                when {
                    monthlyIncome < 3000.0 -> 0.0
                    monthlyIncome < 10000.0 -> (monthlyIncome - 3000.0) * 0.12
                    monthlyIncome < 20000.0 -> 840.0 + (monthlyIncome - 10000.0) * 0.10
                    monthlyIncome < 40000.0 -> 1840.0 + (monthlyIncome - 20000.0) * 0.07
                    else -> 3240.0 // Cap at 3,240 kr for under 66
                }
            }
        }
    }
}
