package se.atte.partier.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.atte.partier.components.CommonCardButton
import se.atte.partier.components.standardPaddingLarge
import se.atte.partier.components.standardPaddingMedium
import se.atte.partier.components.standardPaddingSmall
import se.atte.partier.data.SampleData
import se.atte.partier.theme.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(
    modifier: Modifier = Modifier,
    onNavToExpense: () -> Unit,
    onNavToIncome: () -> Unit,
    onNavToParty: () -> Unit,
) {
    val year = remember { SampleData.year }
    val totalBudget = remember { SampleData.totalBudget }

    Column(modifier = modifier) {
        // Header
        Text(
            modifier = Modifier.padding(bottom = standardPaddingSmall),
            text = "Sveriges Budget $year",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
        )

        Row(
            modifier = Modifier.padding(bottom = standardPaddingLarge),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "${totalBudget.toInt()} miljarder kronor fördelas över olika områden",
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        // View options
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(standardPaddingMedium),
            contentPadding = PaddingValues(bottom = standardPaddingSmall)
        ) {
            item {
                CommonCardButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Utgifter",
                    subTitle = arrayOf("Se statens utgifter fördelade på olika områden och partiernas budgetförslag"),
                    showArrow = true,
                    icon = { Text("💰", style = MaterialTheme.typography.headlineMedium) },
                    onClickEvent = onNavToExpense,
                )
            }
            item {
                CommonCardButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Inkomster",
                    subTitle = arrayOf("Se statens inkomster från skatter, avgifter och andra källor"),
                    showArrow = true,
                    icon = { Text("📈", style = MaterialTheme.typography.headlineMedium) },
                    onClickEvent = onNavToIncome,
                )
            }
            item {
                CommonCardButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Details per parti",
                    subTitle = arrayOf("Se varje partis budgetförslag i detalj"),
                    showArrow = true,
                    icon = { Text("🏛️", style = MaterialTheme.typography.headlineMedium) },
                    onClickEvent = onNavToParty,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewStartScreen() {
    ThemePreview {
        BudgetScreen(
            modifier = Modifier.fillMaxSize().padding(horizontal = standardPaddingMedium),
            onNavToExpense = {},
            onNavToIncome = {},
            onNavToParty = {})
    }
}

@Preview
@Composable
private fun PreviewStartScreen_Dark() {
    ThemePreview(useDarkMode = true) {
        BudgetScreen(
            modifier = Modifier.fillMaxSize().padding(horizontal = standardPaddingMedium),
            onNavToExpense = {},
            onNavToIncome = {},
            onNavToParty = {})
    }
}
