package se.atte.partier.navigation

import androidx.compose.runtime.*
import se.atte.partier.data.BudgetCategory

class NavigationManager {
    private var _currentScreen by mutableStateOf<Screen>(Screen.Start)
    private var _selectedCategory by mutableStateOf<BudgetCategory?>(null)
    private var _previousScreen by mutableStateOf<Screen?>(null)
    
    val currentScreen: Screen get() = _currentScreen
    val selectedCategory: BudgetCategory? get() = _selectedCategory
    
    fun navigateToStart() {
        _previousScreen = _currentScreen
        _currentScreen = Screen.Start
        _selectedCategory = null
    }
    
    fun navigateToBudget() {
        _previousScreen = _currentScreen
        _currentScreen = Screen.Budget
        _selectedCategory = null
    }
    
    fun navigateToUtgifter() {
        _previousScreen = _currentScreen
        _currentScreen = Screen.Utgifter
        _selectedCategory = null
    }
    
    fun navigateToIncome() {
        _previousScreen = _currentScreen
        _currentScreen = Screen.Income
        _selectedCategory = null
    }
    
    fun navigateToByParty() {
        _previousScreen = _currentScreen
        _currentScreen = Screen.ByParty
        _selectedCategory = null
    }
    
    fun navigateToCategory(category: BudgetCategory) {
        _previousScreen = _currentScreen
        _selectedCategory = category
        _currentScreen = Screen.CategoryDetail
    }
    
    fun navigateBack() {
        when (_currentScreen) {
            Screen.CategoryDetail -> {
                // Return to the screen we came from
                _currentScreen = _previousScreen ?: Screen.Utgifter
            }
            Screen.Utgifter, Screen.Income, Screen.ByParty -> _currentScreen = Screen.Budget
            Screen.Budget -> _currentScreen = Screen.Start
            else -> _currentScreen = Screen.Start
        }
        _selectedCategory = null
    }
}

@Composable
fun rememberNavigationManager(): NavigationManager {
    return remember { NavigationManager() }
}
