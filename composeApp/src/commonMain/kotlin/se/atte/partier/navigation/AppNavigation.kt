package se.atte.partier.navigation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import se.atte.partier.getPlatform
import se.atte.partier.screens.ViewType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navigationManager = rememberNavigationManager()
    val currentScreen = navigationManager.currentScreen
    val selectedCategory = navigationManager.selectedCategory
    val isWeb = isWebPlatform()
    
    // Always show navigation bar (except on Start screen)
    val showNavigationBar = currentScreen != Screen.Start
    
    if (isWeb && showNavigationBar) {
        // Web layout with NavigationRail
        Row(modifier = Modifier.fillMaxSize()) {
            NavigationRail(
                modifier = Modifier.fillMaxHeight(),
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                NavigationRailItem(
                    icon = { Icon(Icons.Filled.AccountBalance, contentDescription = "Budget") },
                    label = { Text("Budget") },
                    selected = currentScreen in listOf(Screen.Budget, Screen.Income, Screen.ByParty, Screen.CategoryDetail),
                    onClick = { navigationManager.navigateToBudget() }
                )
            }
            
            AnimatedNavigation(
                currentScreen = currentScreen,
                selectedCategory = selectedCategory,
                selectedParty = navigationManager.selectedParty,
                onCategoryClick = { category ->
                    navigationManager.navigateToCategory(category)
                },
                onPartySelected = { partyCode ->
                    navigationManager.navigateToPartyDetail(partyCode)
                },
                onBackClick = {
                    navigationManager.navigateBack()
                },
                onViewSelected = { viewType ->
                    when (viewType) {
                        ViewType.EXPENSES -> navigationManager.navigateToUtgifter()
                        ViewType.INCOME -> navigationManager.navigateToIncome()
                        ViewType.BY_PARTY -> navigationManager.navigateToByParty()
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }
    } else if (!isWeb && showNavigationBar) {
        // Mobile layout with NavigationBar
        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.AccountBalance, contentDescription = "Budget") },
                        label = { Text("Budget") },
                        selected = currentScreen in listOf(Screen.Budget, Screen.Income, Screen.ByParty, Screen.CategoryDetail),
                        onClick = { navigationManager.navigateToBudget() }
                    )
                }
            }
        ) { paddingValues ->
            AnimatedNavigation(
                currentScreen = currentScreen,
                selectedCategory = selectedCategory,
                selectedParty = navigationManager.selectedParty,
                onCategoryClick = { category ->
                    navigationManager.navigateToCategory(category)
                },
                onPartySelected = { partyCode ->
                    navigationManager.navigateToPartyDetail(partyCode)
                },
                onBackClick = {
                    navigationManager.navigateBack()
                },
                onViewSelected = { viewType ->
                    when (viewType) {
                        ViewType.EXPENSES -> navigationManager.navigateToUtgifter()
                        ViewType.INCOME -> navigationManager.navigateToIncome()
                        ViewType.BY_PARTY -> navigationManager.navigateToByParty()
                    }
                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    } else {
        // No navigation bar (Start screen or CategoryDetail)
        AnimatedNavigation(
            currentScreen = currentScreen,
            selectedCategory = selectedCategory,
            selectedParty = navigationManager.selectedParty,
            onCategoryClick = { category ->
                navigationManager.navigateToCategory(category)
            },
            onPartySelected = { partyCode ->
                navigationManager.navigateToPartyDetail(partyCode)
            },
            onBackClick = {
                navigationManager.navigateBack()
            },
            onViewSelected = { viewType ->
                when (viewType) {
                    ViewType.EXPENSES -> navigationManager.navigateToUtgifter()
                    ViewType.INCOME -> navigationManager.navigateToIncome()
                    ViewType.BY_PARTY -> navigationManager.navigateToByParty()
                }
            }
        )
    }
}

@Composable
private fun isWebPlatform(): Boolean {
    val platform = getPlatform()
    return platform.name.contains("Web", ignoreCase = true)
}

sealed class Screen {
    object Start : Screen()
    object Budget : Screen()
    object Utgifter : Screen()
    object Income : Screen()
    object ByParty : Screen()
    object PartySelection : Screen()
    object PartyDetail : Screen()
    object CategoryDetail : Screen()
}
