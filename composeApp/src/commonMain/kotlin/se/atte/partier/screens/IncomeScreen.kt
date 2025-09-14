package se.atte.partier.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.atte.partier.components.CommonCardButton
import se.atte.partier.components.HeroIcons
import se.atte.partier.components.painter
import se.atte.partier.components.standardPaddingMedium
import se.atte.partier.data.BudgetCategory
import se.atte.partier.data.SampleData
import se.atte.partier.theme.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCategoryClick: (String) -> Unit
) {
    val incomeCategories: List<BudgetCategory> = remember { SampleData.incomeCategories.sortedBy { it.displayOrder } }
    val year = remember { SampleData.year }
    val totalBudget = remember { SampleData.totalBudget }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "Inkomster",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    TextButton(onClick = onBackClick) {
                        Icon(
                            painter = HeroIcons.ArrowBack.painter(),
                            contentDescription = "Tillbaka"
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding).padding(horizontal = standardPaddingMedium),
            verticalArrangement = Arrangement.spacedBy(standardPaddingMedium)
        ) {
            Text(
                text = "Sveriges Inkomster $year",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "${totalBudget.toInt()} miljarder kronor fördelas över ${incomeCategories.size} olika inkomstkällor",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 100.dp) // Extra bottom padding for better scrolling UX
            ) {
                items(incomeCategories) { category ->
                    CommonCardButton(
                        modifier = Modifier.fillMaxWidth(),
                        title = category.name,
                        showArrow = true,
                        subTitle = arrayOf(category.description),
                        onClickEvent = { onCategoryClick(category.id) },
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
private fun PreviewIncomeScreen() {
    ThemePreview {
        IncomeScreen(
            onCategoryClick = {},
            onBackClick = {},
        )
    }
}

