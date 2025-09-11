package se.atte.partier

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.atte.partier.components.BottomNavBar
import se.atte.partier.components.NavRail
import se.atte.partier.components.standardPaddingMedium
import se.atte.partier.data.SampleData
import se.atte.partier.screens.BudgetScreen
import se.atte.partier.screens.ByPartyScreen
import se.atte.partier.screens.CategoryDetailScreen
import se.atte.partier.screens.ExpenseScreen
import se.atte.partier.screens.IncomeScreen
import se.atte.partier.screens.PartyDetailScreen
import se.atte.partier.screens.PartySelectionScreen
import se.atte.partier.theme.AppTheme

@Serializable
sealed class Screen {

    @Serializable
    data object Budget : Screen()

    @Serializable
    data object Expenses : Screen()

    @Serializable
    data object Income : Screen()

    @Serializable
    data class CategoryDetail(val categoryId: String) : Screen()

    @Serializable
    data object ByParty : Screen()

    @Serializable
    data object PartySelection : Screen()

    @Serializable
    data class PartyDetail(val partyCode: String) : Screen()

}

@Composable
@Preview
fun App() {
    AppTheme(useDarkMode = false) {
        BoxWithConstraints {
            val navController = rememberNavController()
            val onBackClick: () -> Unit = { navController.popBackStack() }

            // Use screen width to determine navigation type
            // Phone width < 600dp = bottom nav, Tablet/Desktop >= 600dp = nav rail
            val useBottomNav = maxWidth < 800.dp

            Scaffold(
                bottomBar = {
                    if (useBottomNav) {
                        BottomNavBar(navController = navController)
                    }
                }) { contentPadding ->
                Row(modifier = Modifier.fillMaxSize()) {
                    if (!useBottomNav) {
                        NavRail(navController = navController)
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f) // Takes up remaining space
                            .padding(contentPadding) // Padding from Scaffold (system bars, etc.)
                            .fillMaxSize() // Ensure full size for proper scrolling
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Budget,
                            enterTransition = {
                                slideInHorizontally(initialOffsetX = { it })
                            },
                            popEnterTransition = {
                                slideInHorizontally(initialOffsetX = { -it })
                            },
                            exitTransition = {
                                slideOutHorizontally(targetOffsetX = { -it })
                            },
                            popExitTransition = {
                                slideOutHorizontally(targetOffsetX = { it })
                            }
                        ) {
                            composable<Screen.Budget> {
                                BudgetScreen(
                                    modifier = Modifier.fillMaxSize().padding(horizontal = standardPaddingMedium),
                                    onNavToExpense = { navController.navigate(Screen.Expenses) },
                                    onNavToIncome = { navController.navigate(Screen.Income) },
                                    onNavToParty = { navController.navigate(Screen.PartySelection) }
                                )
                            }
                            composable<Screen.Expenses> {
                                ExpenseScreen(
                                    onCategoryClick = { categoryId ->
                                        navController.navigate(Screen.CategoryDetail(categoryId = categoryId))
                                    },
                                    onBackClick = onBackClick,
                                )
                            }
                            composable<Screen.Income> {
                                IncomeScreen(
                                    onBackClick = onBackClick,
                                    onCategoryClick = { categoryId ->
                                        navController.navigate(Screen.CategoryDetail(categoryId = categoryId))
                                    },
                                )
                            }
                            composable<Screen.CategoryDetail> {
                                val categoryId = it.toRoute<Screen.CategoryDetail>().categoryId
                                SampleData.getCategoryById(categoryId)?.let { category ->
                                    CategoryDetailScreen(
                                        category = category,
                                        onBackClick = onBackClick,
                                    )
                                } ?: run {
                                    // If category is not found, just go back
                                    onBackClick()
                                }
                            }
                            composable<Screen.ByParty> {
                                ByPartyScreen(onBackClick = onBackClick)
                            }
                            composable<Screen.PartySelection> {
                                PartySelectionScreen(
                                    onBackClick = onBackClick,
                                    onPartySelected = { partyCode ->
                                        navController.navigate(Screen.PartyDetail(partyCode))
                                    },
                                )
                            }
                            composable<Screen.PartyDetail> {
                                val partyCode = it.toRoute<Screen.PartyDetail>().partyCode
                                PartyDetailScreen(
                                    partyCode = partyCode,
                                    onBackClick = onBackClick,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}