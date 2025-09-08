package se.atte.partier.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
// Icons removed for now - will add back when needed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import partier.composeapp.generated.resources.Res
import partier.composeapp.generated.resources.nav_budget
import se.atte.partier.data.BudgetCategory
import se.atte.partier.screens.BudgetScreen
import se.atte.partier.screens.CategoryDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navigationManager = rememberNavigationManager()
    val currentScreen = navigationManager.currentScreen
    val selectedCategory = navigationManager.selectedCategory
    
    val isWeb = isWebPlatform()
    
    if (isWeb) {
        // Web layout with top navigation
        Row(modifier = Modifier.fillMaxSize()) {
            // Top navigation bar for web
            Column(modifier = Modifier.fillMaxHeight()) {
                NavigationRail(
                    modifier = Modifier.fillMaxHeight(),
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    NavigationRailItem(
                        icon = { Text("💰") },
                        label = { Text(stringResource(Res.string.nav_budget)) },
                        selected = currentScreen == Screen.Budget,
                        onClick = { 
                            navigationManager.navigateToBudget()
                        }
                    )
                }
            }
            
            // Main content area
            AnimatedNavigation(
                modifier = Modifier.fillMaxSize(),
                currentScreen = currentScreen,
                selectedCategory = selectedCategory,
                onCategoryClick = { category ->
                    navigationManager.navigateToCategory(category)
                },
                onBackClick = {
                    navigationManager.navigateBack()
                },
            )
        }
    } else {
        // Mobile layout with bottom navigation
        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Text("💰") },
                        label = { Text(stringResource(Res.string.nav_budget)) },
                        selected = currentScreen == Screen.Budget,
                        onClick = { 
                            navigationManager.navigateToBudget()
                        }
                    )
                }
            }
        ) { paddingValues ->
            AnimatedNavigation(
                currentScreen = currentScreen,
                selectedCategory = selectedCategory,
                onCategoryClick = { category ->
                    navigationManager.navigateToCategory(category)
                },
                onBackClick = {
                    navigationManager.navigateBack()
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )
        }
    }
}

@Composable
private fun isWebPlatform(): Boolean {
    // For now, assume web platform - this can be improved with proper platform detection
    return true
}

sealed class Screen {
    object Budget : Screen()
    object CategoryDetail : Screen()
}
