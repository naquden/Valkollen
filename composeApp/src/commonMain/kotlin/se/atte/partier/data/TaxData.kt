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
    val taxProposals: TaxProposalDetails
)

@Serializable
data class TaxProposalDetails(
    val incomeTax: IncomeTaxDetails,
    val deductions: DeductionDetails
)

@Serializable
data class IncomeTaxDetails(
    val marginalTaxRate: Double,
    val municipalTaxRate: Double,
    val totalTaxRate: Double,
    val description: String
)

@Serializable
data class DeductionDetails(
    val jobbskatteavdrag: Double,
    val grundavdrag: Int,
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
