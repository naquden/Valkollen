package se.atte.partier.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import se.atte.partier.data.BudgetCategory
import se.atte.partier.screens.BudgetScreen
import se.atte.partier.screens.UtgifterScreen
import se.atte.partier.screens.CategoryDetailScreen
import se.atte.partier.screens.StartScreen
import se.atte.partier.screens.IncomeScreen
import se.atte.partier.screens.ByPartyScreen
import se.atte.partier.screens.PartySelectionScreen
import se.atte.partier.screens.PartyDetailScreen
import se.atte.partier.screens.ViewType

@Composable
fun AnimatedNavigation(
    currentScreen: Screen,
    selectedCategory: BudgetCategory?,
    selectedParty: String?,
    onCategoryClick: (BudgetCategory) -> Unit,
    onPartySelected: (String) -> Unit,
    onBackClick: () -> Unit,
    onViewSelected: (ViewType) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = {
                when {
                    // Going to CategoryDetail (forward navigation)
                    initialState == Screen.Budget && targetState == Screen.CategoryDetail -> {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300)) togetherWith
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    }
                    // Going back to Budget (back navigation)
                    initialState == Screen.CategoryDetail && targetState == Screen.Budget -> {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300)) togetherWith
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    }
                    // Default case
                    else -> {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300)) togetherWith
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { screen ->
            when (screen) {
                Screen.Start -> {
                    StartScreen(onViewSelected = onViewSelected)
                }
                Screen.Budget -> {
                    BudgetScreen(
                        onCategoryClick = onCategoryClick,
                        onBackClick = onBackClick,
                        onViewSelected = onViewSelected
                    )
                }
                Screen.Utgifter -> {
                    UtgifterScreen(
                        onCategoryClick = onCategoryClick,
                        onBackClick = onBackClick
                    )
                }
                Screen.CategoryDetail -> {
                    selectedCategory?.let { category ->
                        CategoryDetailScreen(
                            category = category,
                            onBackClick = onBackClick
                        )
                    }
                }
                Screen.Income -> {
                    IncomeScreen(
                        onBackClick = onBackClick,
                        onCategoryClick = onCategoryClick
                    )
                }
                Screen.ByParty -> {
                    ByPartyScreen(onBackClick = onBackClick)
                }
                Screen.PartySelection -> {
                    PartySelectionScreen(
                        onBackClick = onBackClick,
                        onPartySelected = onPartySelected
                    )
                }
                Screen.PartyDetail -> {
                    selectedParty?.let { partyCode ->
                        PartyDetailScreen(
                            partyCode = partyCode,
                            onBackClick = onBackClick
                        )
                    }
                }
            }
        }
    }
}
