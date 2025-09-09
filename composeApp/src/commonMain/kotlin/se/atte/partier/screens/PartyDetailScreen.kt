package se.atte.partier.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compare
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import partier.composeapp.generated.resources.Res
import partier.composeapp.generated.resources.billion_kr
import partier.composeapp.generated.resources.nav_back
import se.atte.partier.components.CommonCard
import se.atte.partier.constants.Party
import se.atte.partier.data.SampleData
import se.atte.partier.components.PartyComparisonDialog
import se.atte.partier.components.standardPaddingMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartyDetailScreen(
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

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top App Bar
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
                    Text(stringResource(Res.string.nav_back))
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Party Header
            item {
                CommonCard(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = partyName,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = partyColor
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Budgetförslag ${SampleData.year}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Total budget: ${SampleData.totalBudget} ${stringResource(Res.string.billion_kr)}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { showComparisonDialog = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Compare,
                                contentDescription = "Jämför",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("Jämför med annat parti")
                        }
                    }
                }
            }

            // Budget Summary
            if (budgetData.isNotEmpty()) {
                item {
                    CommonCard(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Utgifter per kategori",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Text(
                                text = "Total utgifter: ${budgetData.sumOf { it.budgetAmount }.toInt()} miljarder kr",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "Antal kategorier: ${budgetData.size}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Income Summary
            if (incomeData.isNotEmpty()) {
                item {
                    CommonCard(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Inkomster per kategori",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Text(
                                text = "Total inkomster: ${incomeData.sumOf { it.budgetAmount }.toInt()} miljarder kr",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "Antal kategorier: ${incomeData.size}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Budget Categories List
            if (budgetData.isNotEmpty()) {
                item {
                    Text(
                        text = "Detaljerade utgifter",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                items(budgetData) { item ->
                    SimpleCategoryCard(
                        label = item.categoryName,
                        value = item.budgetAmount
                    )
                }
            }

            // Income Categories List
            if (incomeData.isNotEmpty()) {
                item {
                    Text(
                        text = "Detaljerade inkomster",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                items(incomeData) { item ->
                    SimpleCategoryCard(
                        label = item.categoryName,
                        value = item.budgetAmount
                    )
                }

                item { Spacer(modifier = Modifier.height(standardPaddingMedium)) }
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
private fun SimpleCategoryCard(
    label: String,
    value: Double
) {
    CommonCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Simple bullet point
            Text(
                text = "•",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(12.dp))

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
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private data class PartyDetailItem(
    val partyCode: String,
    val budgetAmount: Double,
    val sourceUrl: String,
    val categoryName: String
)
