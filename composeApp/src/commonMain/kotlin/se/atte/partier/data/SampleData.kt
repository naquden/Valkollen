package se.atte.partier.data

object SampleData {
    private var budgetData: BudgetData? = null
    
    suspend fun initialize() {
        if (budgetData == null) {
            budgetData = JsonFileLoader.loadBudgetData()
        }
    }
    
    val budgetCategories: List<BudgetCategory>
        get() = budgetData?.budgetCategories ?: emptyList()

    val year: Int
        get() = budgetData?.year ?: 2025

    val totalBudget: Double
        get() = budgetData?.totalBudget ?: 0.0

    val incomeCategories: List<BudgetCategory>
        get() = budgetData?.incomeCategories ?: emptyList()

    fun getCategoryById(categoryId: String): BudgetCategory? {
        // First try budget categories
        val budgetCategory = budgetCategories.find { it.id == categoryId }
        if (budgetCategory != null) {
            return budgetCategory
        }

        // Then try income categories
        val incomeCategory = incomeCategories.find { it.id == categoryId }
        if (incomeCategory != null) {
            return incomeCategory
        }

        return null
    }

    fun getPartyBudgetsForCategory(categoryId: String): List<PartyBudget> {
        return getCategoryById(categoryId)?.partyBudgets ?: emptyList()
    }
}
