package se.atte.partier.data

object SampleData {
    private val budgetData by lazy { JsonFileLoader.loadBudgetData() }
    
    val budgetCategories: List<BudgetCategory>
        get() = budgetData.budgetCategories

    val year: Int
        get() = budgetData.year

    val totalBudget: Double
        get() = budgetData.totalBudget

    val incomeCategories: List<BudgetCategory>
        get() = budgetData.incomeCategories

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
