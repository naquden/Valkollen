package se.atte.partier.data

object SampleData {
    private val budgetData by lazy { JsonFileLoader.loadBudgetData() }
    
    val budgetCategories: List<BudgetCategory>
        get() = budgetData.budgetCategories
    
    val politicalParties: List<String>
        get() = budgetData.politicalParties
    
    val year: Int
        get() = budgetData.year
    
    val totalBudget: Double
        get() = budgetData.totalBudget
    
    fun getPartyBudgetsForCategory(categoryId: String): List<PartyBudget> {
        return budgetCategories
            .find { it.id == categoryId }
            ?.partyBudgets
            ?: emptyList()
    }
}
