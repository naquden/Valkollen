package se.atte.partier.data

import kotlinx.serialization.Serializable

@Serializable
data class TaxData(
    val year: Int,
    val description: String,
    val parties: List<PartyTaxProposal>,
    val calculationMethod: CalculationMethod
)

@Serializable
data class PartyTaxProposal(
    val partyCode: String,
    val partyName: String,
    val taxProposals: TaxProposalDetails,
    val sources: List<String> = emptyList()
)

@Serializable
data class TaxProposalDetails(
    val incomeTax: IncomeTaxDetails,
    val deductions: DeductionDetails,
    val additionalTaxes: AdditionalTaxes
)

@Serializable
data class IncomeTaxDetails(
    val marginalTaxRate: Double,
    val municipalTaxRate: Double,
    val totalTaxRate: Double,
    val highIncomeThreshold: Int,
    val highIncomeTaxRate: Double,
    val varnskatt: Double? = null, // Värnskatt - endast för Vänsterpartiet
    val varnskattThreshold: Int? = null, // Brytpunkt för värnskatt
    val description: String
)

@Serializable
data class DeductionDetails(
    val jobbskatteavdrag: Double,
    val grundavdrag: Int,
    val description: String
)

@Serializable
data class AdditionalTaxes(
    val begravningsavgift: Double,
    val publicServiceAvgift: Double,
    val kommunalInkomstskatt: Double,
    val allmanPensionsavgift: Double,
    val kyrkoavgift: Double,
    val trossamfundsavgift: Double,
    val description: String
)

@Serializable
data class CalculationMethod(
    val description: String,
    val formula: String,
    val note: String
)

@Serializable
data class PartyTaxCalculation(
    val partyCode: String,
    val partyName: String,
    val grossSalary: Double,
    val totalTaxRate: Double,
    val jobbskatteavdrag: Double,
    val grundavdrag: Int,
    val netSalary: Double,
    val taxAmount: Double,
    val effectiveTaxRate: Double
)
