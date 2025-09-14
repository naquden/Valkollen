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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.atte.partier.components.CommonCard
import se.atte.partier.components.CommonCardButton
import se.atte.partier.components.HeroIcons
import se.atte.partier.components.painter
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
            modifier = Modifier.padding(vertical = standardPaddingSmall),
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

        // Help card
        CommonCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = standardPaddingLarge)
        ) {
            Column(
                modifier = Modifier.padding(standardPaddingMedium)
            ) {
                Text(
                    text = "Välkommen till Sveriges Budget 2025",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Text(
                    modifier = Modifier.padding(top = standardPaddingSmall),
                    text = "Upptäck hur staten spenderar dina skattepengar och jämför partiernas budgetförslag",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Text(
                    modifier = Modifier.padding(top = standardPaddingMedium),
                    text = "Utgifter - Utforska 27 olika budgetområden som sjukvård, skola, försvar och infrastruktur. Se exakt hur mycket som spenderas på varje område och jämför hur olika partier vill fördela pengarna.",
                    style = MaterialTheme.typography.bodySmall
                )
                
                Text(
                    modifier = Modifier.padding(top = standardPaddingSmall),
                    text = "Inkomster - Lär dig var statens pengar kommer ifrån - skatter, avgifter och andra inkomstkällor.",
                    style = MaterialTheme.typography.bodySmall
                )
                
                Text(
                    modifier = Modifier.padding(top = standardPaddingSmall),
                    text = "Per parti - Jämför partiernas budgetförslag sida vid sida. Se vilka prioriteringar varje parti har och hur de vill spendera dina skattepengar.",
                    style = MaterialTheme.typography.bodySmall
                )
                
                Text(
                    modifier = Modifier.padding(top = standardPaddingMedium),
                    text = "Tips: Tryck på valfritt område för att se detaljerade uppdelningar och partijämförelser. Alla siffror visas i miljarder kronor för enkel jämförelse.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Text(
                    modifier = Modifier.padding(top = standardPaddingMedium),
                    text = "Obs: Denna app är under utveckling. Full funktionalitet kommer när partierna publicerat sina officiella budgetförslag inför nästa val.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
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
                    icon = { Icon(painter = HeroIcons.Expense.painter(), contentDescription = null) },
                    onClickEvent = onNavToExpense,
                )
            }
            item {
                CommonCardButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Inkomster",
                    subTitle = arrayOf("Se statens inkomster från skatter, avgifter och andra källor"),
                    showArrow = true,
                    icon = { Icon(painter = HeroIcons.Income.painter(), contentDescription = null) },
                    onClickEvent = onNavToIncome,
                )
            }
            item {
                CommonCardButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Details per parti",
                    subTitle = arrayOf("Se varje partis budgetförslag i detalj"),
                    showArrow = true,
                    icon = { Icon(painter = HeroIcons.PartyDetails.painter(), contentDescription = null) },
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
