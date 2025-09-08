package se.atte.partier.data

import kotlinx.serialization.Serializable

@Serializable
data class BudgetCategory(
    val id: String,
    val name: String,
    val description: String,
    val partyBudgets: List<PartyBudget>
)

@Serializable
data class PartyBudget(
    val partyName: String,
    val partyCode: String,
    val budgetAmount: Double,
    val sourceUrl: String
)

@Serializable
data class IncomeType(
    val id: String,
    val name: String,
    val description: String,
    val amount: Double,
    val percentage: Double
)

@Serializable
data class PartyIncome(
    val partyName: String,
    val partyCode: String,
    val incomeTypes: List<IncomeType>,
    val totalIncome: Double,
    val sourceUrl: String
)

@Serializable
data class BudgetData(
    val year: Int,
    val totalBudget: Double,
    val budgetCategories: List<BudgetCategory>,
    val politicalParties: List<String>,
    val incomeCategories: List<BudgetCategory>
)
