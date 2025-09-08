package se.atte.partier.navigation

import androidx.compose.runtime.*
import se.atte.partier.data.BudgetCategory

class NavigationManager {
    private var _currentScreen by mutableStateOf<Screen>(Screen.Start)
    private var _selectedCategory by mutableStateOf<BudgetCategory?>(null)
    private var _selectedParty by mutableStateOf<String?>(null)
    private var _previousScreen by mutableStateOf<Screen?>(null)
    
    val currentScreen: Screen get() = _currentScreen
    val selectedCategory: BudgetCategory? get() = _selectedCategory
    val selectedParty: String? get() = _selectedParty
    
    fun navigateToStart() {
        _previousScreen = _currentScreen
        _currentScreen = Screen.Start
        _selectedCategory = null
        _selectedParty = null
    }
    
    fun navigateToBudget() {
        _previousScreen = _currentScreen
        _currentScreen = Screen.Budget
        _selectedCategory = null
        _selectedParty = null
    }
    
    fun navigateToUtgifter() {
        _previousScreen = _currentScreen
        _currentScreen = Screen.Utgifter
        _selectedCategory = null
        _selectedParty = null
    }
    
    fun navigateToIncome() {
        _previousScreen = _currentScreen
        _currentScreen = Screen.Income
        _selectedCategory = null
        _selectedParty = null
    }
    
    fun navigateToByParty() {
        _previousScreen = _currentScreen
        _currentScreen = Screen.PartySelection
        _selectedCategory = null
        _selectedParty = null
    }
    
    fun navigateToPartySelection() {
        _previousScreen = _currentScreen
        _currentScreen = Screen.PartySelection
        _selectedCategory = null
        _selectedParty = null
    }
    
    fun navigateToPartyDetail(partyCode: String) {
        _previousScreen = _currentScreen
        _selectedParty = partyCode
        _currentScreen = Screen.PartyDetail
        _selectedCategory = null
    }
    
    fun navigateToCategory(category: BudgetCategory) {
        _previousScreen = _currentScreen
        _selectedCategory = category
        _currentScreen = Screen.CategoryDetail
        _selectedParty = null
    }
    
    fun navigateBack() {
        when (_currentScreen) {
            Screen.CategoryDetail -> {
                // Return to the screen we came from
                _currentScreen = _previousScreen ?: Screen.Utgifter
            }
            Screen.PartyDetail -> {
                // Return to party selection
                _currentScreen = Screen.PartySelection
            }
            Screen.PartySelection -> {
                // Return to budget screen
                _currentScreen = Screen.Budget
            }
            Screen.Utgifter, Screen.Income, Screen.ByParty -> _currentScreen = Screen.Budget
            Screen.Budget -> _currentScreen = Screen.Start
            else -> _currentScreen = Screen.Start
        }
        _selectedCategory = null
        _selectedParty = null
    }
}

@Composable
fun rememberNavigationManager(): NavigationManager {
    return remember { NavigationManager() }
}
