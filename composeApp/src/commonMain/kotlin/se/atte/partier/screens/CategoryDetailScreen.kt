package se.atte.partier.screens

// Icons removed for now - will add back when needed
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import partier.composeapp.generated.resources.Res
import partier.composeapp.generated.resources.billion_kr
import partier.composeapp.generated.resources.budget_proposal
import partier.composeapp.generated.resources.nav_back
import partier.composeapp.generated.resources.parties_budget_proposals
import se.atte.partier.components.CommonCard
import se.atte.partier.components.standardPaddingMedium
import se.atte.partier.components.standardPaddingSmall
import se.atte.partier.constants.Party
import se.atte.partier.data.BudgetCategory
import se.atte.partier.data.PartyBudget
import se.atte.partier.data.SampleData
import se.atte.partier.theme.ThemePreview
import se.atte.partier.utils.openUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(
    modifier: Modifier = Modifier,
    category: BudgetCategory,
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    TextButton(onClick = onBackClick) {
                        Text(stringResource(Res.string.nav_back))
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding).padding(horizontal = standardPaddingMedium),
            verticalArrangement = Arrangement.spacedBy(standardPaddingMedium)
        ) {
            val partyBudgets = remember(category.id) {
                SampleData.getPartyBudgetsForCategory(category.id)
            }
            CommonCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            ) {
                Text(
                    modifier = Modifier.padding(standardPaddingMedium),
                    text = category.description,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    modifier = Modifier.padding(standardPaddingMedium),
                    text = stringResource(Res.string.budget_proposal),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            if (partyBudgets.isNotEmpty()) {
                Text(
                    text = stringResource(Res.string.parties_budget_proposals),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )

                BudgetBarChart(
                    modifier = Modifier.fillMaxWidth(),
                    partyBudgets = partyBudgets,
                )
            } else {
                // No data available message
                EmptyScreen()
            }
        }
    }
}

@Composable
fun BudgetBarChart(
    modifier: Modifier = Modifier,
    partyBudgets: List<PartyBudget>,
) {
    if (partyBudgets.isEmpty()) return

    val maxBudget = partyBudgets.maxOfOrNull { it.budgetAmount } ?: 0.0
    val minBudget = partyBudgets.minOfOrNull { it.budgetAmount } ?: 0.0
    val budgetRange = maxBudget - minBudget

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(standardPaddingSmall)
    ) {
        itemsIndexed(partyBudgets) { index, partyBudget ->
            BarChartItem(
                modifier = Modifier.fillMaxWidth(),
                partyBudget = partyBudget,
                maxBudget = maxBudget,
                minBudget = minBudget,
                budgetRange = budgetRange,
                color = getPartyColor(partyBudget.partyCode),
            )
        }
        item { Spacer(modifier = Modifier.height(standardPaddingMedium)) }
    }
}

@Composable
private fun BarChartItem(
    partyBudget: PartyBudget,
    maxBudget: Double,
    minBudget: Double,
    budgetRange: Double,
    color: Color,
    modifier: Modifier = Modifier
) {
    // Use absolute values instead of normalized to ensure all bars are visible
    val normalizedValue = if (maxBudget > 0) {
        kotlin.math.abs(partyBudget.budgetAmount) / maxBudget
    } else 1.0

    CommonCard(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(standardPaddingMedium)
        ) {
            // Party name and amount
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = Party.fromCode(partyBudget.partyCode)?.displayName ?: partyBudget.partyCode,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "${(partyBudget.budgetAmount * 10).toInt() / 10.0} ${stringResource(Res.string.billion_kr)}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        imageVector = Icons.Default.Link,
                        contentDescription = "Öppna källa",
                        modifier = Modifier
                            .size(16.dp)
                            .clickable { openUrl(partyBudget.sourceUrl) },
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Bar chart
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val barWidth = size.width * normalizedValue.toFloat()
                    val barHeight = size.height

                    // Background bar (light gray)
                    drawRect(
                        color = Color.Gray.copy(alpha = 0.2f),
                        topLeft = Offset.Zero,
                        size = Size(size.width, barHeight)
                    )

                    // Main bar
                    drawRect(
                        color = color,
                        topLeft = Offset.Zero,
                        size = Size(barWidth, barHeight)
                    )

                    // Value text on the bar (simplified for cross-platform)
                    if (barWidth > 60) { // Only show text if bar is wide enough
                        // Text will be handled by Compose Text component instead
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyScreen(modifier: Modifier = Modifier) {
    CommonCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "📊",
                style = MaterialTheme.typography.displaySmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Inga budgetförslag tillgängliga för denna kategori ännu",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Data kommer snart när partierna publicerar sina budgetförslag",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun getPartyColor(partyCode: String): Color {
    return Party.getColorByCode(partyCode)
}

@Preview
@Composable
fun PreviewCategoryDetailScreen() {
    ThemePreview {
        CategoryDetailScreen(modifier = Modifier.fillMaxSize(), category = SampleData.budgetCategories.first(), onBackClick = {})
    }
}

@Preview
@Composable
fun PreviewCategoryDetailScreen_Dark() {
    ThemePreview(useDarkMode = true) {
        CategoryDetailScreen(modifier = Modifier.fillMaxSize(), category = SampleData.budgetCategories.first(), onBackClick = {})
    }
}

@Preview
@Composable
fun PreviewEmptyCategoryDetailScreen() {
    ThemePreview {
        EmptyScreen(modifier = Modifier.fillMaxWidth())
    }
}