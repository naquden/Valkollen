package se.atte.partier.navigation

import androidx.compose.runtime.*
import se.atte.partier.data.BudgetCategory

class NavigationManager {
    private var _currentScreen by mutableStateOf<Screen>(Screen.Budget)
    private var _selectedCategory by mutableStateOf<BudgetCategory?>(null)
    
    val currentScreen: Screen get() = _currentScreen
    val selectedCategory: BudgetCategory? get() = _selectedCategory
    
    fun navigateToCategory(category: BudgetCategory) {
        _selectedCategory = category
        _currentScreen = Screen.CategoryDetail
    }
    
    fun navigateBack() {
        _currentScreen = Screen.Budget
        _selectedCategory = null
    }
    
    fun navigateToBudget() {
        _currentScreen = Screen.Budget
        _selectedCategory = null
    }
}

@Composable
fun rememberNavigationManager(): NavigationManager {
    return remember { NavigationManager() }
}
