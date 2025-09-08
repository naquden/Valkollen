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

    val incomeCategories: List<BudgetCategory>
        get() = budgetData.incomeCategories
    
    // Get income categories (no percentages needed for income)
    val incomeCategoriesWithPercentages: List<BudgetCategory>
        get() = budgetData.incomeCategories
    
    fun getPartyBudgetsForCategory(categoryId: String): List<PartyBudget> {
        // First try budget categories
        val budgetCategory = budgetCategories.find { it.id == categoryId }
        if (budgetCategory != null) {
            return budgetCategory.partyBudgets
        }
        
        // Then try income categories
        val incomeCategory = incomeCategories.find { it.id == categoryId }
        if (incomeCategory != null) {
            return incomeCategory.partyBudgets
        }
        
        return emptyList()
    }
}
