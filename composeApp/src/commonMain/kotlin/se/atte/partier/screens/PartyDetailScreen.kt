package se.atte.partier.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.atte.partier.components.CommonCard
import se.atte.partier.components.CommonCardButton
import se.atte.partier.components.HeroIcons
import se.atte.partier.components.PartyComparisonDialog
import se.atte.partier.components.painter
import se.atte.partier.components.standardPaddingMedium
import se.atte.partier.components.standardPaddingSmall
import se.atte.partier.constants.Party
import se.atte.partier.data.SampleData
import se.atte.partier.theme.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartyDetailScreen(
    modifier: Modifier = Modifier,
    partyCode: String,
    onBackClick: () -> Unit
) {
    var showComparisonDialog by remember { mutableStateOf(false) }
    val partyName = Party.fromCode(partyCode)?.displayName ?: "Okänt parti"
    val partyColor = Party.getColorByCode(partyCode)

    // Get party's budget data
    val budgetData = remember(partyCode) {
        try {
            SampleData.budgetCategories.sortedBy { it.displayOrder }.mapNotNull { category ->
                val partyBudget = category.partyBudgets.find { it.partyCode == partyCode }
                if (partyBudget != null && partyBudget.budgetAmount > 0) {
                    PartyDetailItem(
                        partyCode = partyBudget.partyCode,
                        budgetAmount = partyBudget.budgetAmount,
                        sourceUrl = partyBudget.sourceUrl,
                        categoryName = category.name
                    )
                } else null
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Get party's income data
    val incomeData = remember(partyCode) {
        try {
            SampleData.incomeCategories.sortedBy { it.displayOrder }.mapNotNull { category ->
                val partyIncome = category.partyBudgets.find { it.partyCode == partyCode }
                if (partyIncome != null && partyIncome.budgetAmount > 0) {
                    PartyDetailItem(
                        partyCode = partyIncome.partyCode,
                        budgetAmount = partyIncome.budgetAmount,
                        sourceUrl = partyIncome.sourceUrl,
                        categoryName = category.name
                    )
                } else null
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = partyName,
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
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .padding(horizontal = standardPaddingMedium),
            verticalArrangement = Arrangement.spacedBy(standardPaddingMedium)
        ) {
            item { Spacer(modifier = Modifier.height(standardPaddingMedium)) }
            // Party Header
            item {
                CommonCard(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.padding(standardPaddingMedium),
                        verticalArrangement = Arrangement.spacedBy(standardPaddingMedium)
                    ) {
                        Text(
                            text = partyName,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = partyColor
                        )

                        Text(
                            text = "Budgetförslag ${SampleData.year}",
                            style = MaterialTheme.typography.titleMedium,
                        )

                        CommonCardButton(
                            modifier = Modifier.fillMaxWidth(),
                            title = "Jämför med annat parti",
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Compare,
                                    contentDescription = "Jämför"
                                )
                            },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            onClickEvent = { showComparisonDialog = true }
                        )
                    }
                }
            }

            // Budget Summary
            if (budgetData.isNotEmpty()) {
                item {
                    CommonCard {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(standardPaddingMedium)
                        ) {
                            Text(
                                modifier = Modifier.padding(bottom = standardPaddingSmall),
                                text = "Utgifter & inkomster",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                            )

                            Text(
                                text = "Total utgifter: ${budgetData.sumOf { it.budgetAmount }.toInt()} miljarder kr",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "Total inkomster: ${incomeData.sumOf { it.budgetAmount }.toInt()} miljarder kr",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            // Budget Categories Bar Chart
            if (budgetData.isNotEmpty()) {
                item {
                    Text(
                        modifier = Modifier.padding(vertical = standardPaddingSmall),
                        text = "Utgiftsfördelning per kategori",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }

                item {
                    BudgetAllocationChart(
                        modifier = Modifier.fillMaxWidth(),
                        budgetData = budgetData,
                        partyColor = partyColor,
                    )
                }
            }

            // Income Categories Bar Chart
            if (incomeData.isNotEmpty()) {
                item {
                    Text(
                        text = "Inkomstfördelning per kategori",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = standardPaddingSmall)
                    )
                }

                item {
                    IncomeAllocationChart(
                        modifier = Modifier.fillMaxWidth(),
                        incomeData = incomeData,
                        partyColor = partyColor,
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(standardPaddingMedium))
                }
            }
        }
    }

    // Comparison Dialog
    if (showComparisonDialog) {
        PartyComparisonDialog(
            currentPartyCode = partyCode,
            onDismiss = { showComparisonDialog = false }
        )
    }
}

@Composable
private fun IncomeAllocationChart(
    modifier: Modifier = Modifier,
    incomeData: List<PartyDetailItem>,
    partyColor: Color,
) {
    val totalIncome = incomeData.sumOf { it.budgetAmount }
    val sortedData = incomeData.sortedByDescending { it.budgetAmount }

    CommonCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(standardPaddingMedium),
            verticalArrangement = Arrangement.spacedBy(standardPaddingMedium)
        ) {
            Text(
                text = "Total inkomst: ${totalIncome.toInt()} miljarder kr",
                style = MaterialTheme.typography.bodyMedium,
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(standardPaddingSmall)
            ) {
                sortedData.forEach { item ->
                    IncomeAllocationBar(
                        categoryName = item.categoryName,
                        amount = item.budgetAmount,
                        totalIncome = totalIncome,
                        partyColor = partyColor
                    )
                }
            }
        }
    }
}

@Composable
private fun IncomeAllocationBar(
    categoryName: String,
    amount: Double,
    totalIncome: Double,
    partyColor: Color
) {
    val percentage = if (totalIncome > 0) amount / totalIncome else 0.0

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Category name and amount
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = categoryName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "${amount.toInt()} miljarder kr",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Bar chart
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                val barHeight = canvasHeight * 0.8f
                val barY = (canvasHeight - barHeight) / 2f

                // Background bar
                drawRect(
                    color = Color.Gray.copy(alpha = 0.2f),
                    topLeft = Offset(0f, barY),
                    size = Size(canvasWidth, barHeight)
                )

                // Main bar
                val barWidth = canvasWidth * percentage.toFloat()
                drawRect(
                    color = partyColor,
                    topLeft = Offset(0f, barY),
                    size = Size(barWidth, barHeight)
                )
            }
        }

        // Percentage text
        Text(
            text = "${(percentage * 100).toInt()}%",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
private fun BudgetAllocationChart(
    budgetData: List<PartyDetailItem>,
    partyColor: Color,
    modifier: Modifier = Modifier
) {
    val totalBudget = budgetData.sumOf { it.budgetAmount }
    val sortedData = budgetData.sortedByDescending { it.budgetAmount }

    CommonCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(standardPaddingMedium)
        ) {
            Text(
                text = "Total budget: ${totalBudget.toInt()} miljarder kr",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = standardPaddingMedium)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(standardPaddingSmall)
            ) {
                sortedData.forEach { item ->
                    BudgetAllocationBar(
                        categoryName = item.categoryName,
                        amount = item.budgetAmount,
                        totalBudget = totalBudget,
                        partyColor = partyColor
                    )
                }
            }
        }
    }
}

@Composable
private fun BudgetAllocationBar(
    categoryName: String,
    amount: Double,
    totalBudget: Double,
    partyColor: Color
) {
    val percentage = if (totalBudget > 0) amount / totalBudget else 0.0

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Category name and amount
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = categoryName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "${amount.toInt()} miljarder kr",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Bar chart
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                val barHeight = canvasHeight * 0.8f
                val barY = (canvasHeight - barHeight) / 2f

                // Background bar
                drawRect(
                    color = Color.Gray.copy(alpha = 0.2f),
                    topLeft = Offset(0f, barY),
                    size = Size(canvasWidth, barHeight)
                )

                // Main bar
                val barWidth = canvasWidth * percentage.toFloat()
                drawRect(
                    color = partyColor,
                    topLeft = Offset(0f, barY),
                    size = Size(barWidth, barHeight)
                )
            }
        }

        // Percentage text
        Text(
            text = "${(percentage * 100).toInt()}%",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
private fun SimpleCategoryCard(
    label: String,
    value: Double
) {
    CommonCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(standardPaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Simple bullet point
            Text(
                text = "•",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(standardPaddingSmall))

            // Category info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "${value.toInt()} miljarder kr",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPartyDetailScreen() {
    ThemePreview {
        PartyDetailScreen(
            partyCode = "SD",
            onBackClick = {}
        )
    }
}

@Preview
@Composable
private fun PreviewPartyDetailScreen_Dark() {
    ThemePreview(useDarkMode = true) {
        PartyDetailScreen(
            partyCode = "M",
            onBackClick = {}
        )
    }
}

private data class PartyDetailItem(
    val partyCode: String,
    val budgetAmount: Double,
    val sourceUrl: String,
    val categoryName: String
)
