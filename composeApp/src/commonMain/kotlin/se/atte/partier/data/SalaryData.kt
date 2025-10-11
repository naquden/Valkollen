package se.atte.partier.data

import kotlinx.serialization.Serializable

@Serializable
data class PartyTaxRate(
    val partyCode: String,
    val taxRate: Double, // As decimal (0.33 = 33%)
    val displayName: String
)

@Serializable
data class SalaryCalculation(
    val grossSalary: Double,
    val netSalary: Double,
    val taxAmount: Double,
    val taxRate: Double
)

@Serializable
data class PartySalaryComparison(
    val partyCode: String,
    val partyName: String,
    val taxRate: Double,
    val netSalary: Double,
    val taxAmount: Double
)

