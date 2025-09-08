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
import se.atte.partier.screens.CategoryDetailScreen

@Composable
fun AnimatedNavigation(
    currentScreen: Screen,
    selectedCategory: BudgetCategory?,
    onCategoryClick: (BudgetCategory) -> Unit,
    onBackClick: () -> Unit,
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
                Screen.Budget -> {
                    BudgetScreen(onCategoryClick = onCategoryClick)
                }
                Screen.CategoryDetail -> {
                    selectedCategory?.let { category ->
                        CategoryDetailScreen(
                            category = category,
                            onBackClick = onBackClick
                        )
                    }
                }
            }
        }
    }
}
