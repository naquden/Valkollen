package se.atte.partier.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import se.atte.partier.constants.Party
import se.atte.partier.data.SampleData

@Composable
fun PartyComparisonDialog(
    currentPartyCode: String,
    onDismiss: () -> Unit
) {
    var selectedPartyCode by remember { mutableStateOf<String?>(null) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        CommonCard(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.9f),
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(standardPaddingMedium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (selectedPartyCode == null) "Välj parti att jämföra med" else "Budgetjämförelse",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Row {
                        if (selectedPartyCode != null) {
                            TextButton(
                                onClick = { selectedPartyCode = null }
                            ) {
                                Text("← Tillbaka")
                            }
                        }

                        TextButton(onClick = onDismiss) {
                            Text("Stäng")
                        }
                    }
                }

                HorizontalDivider()

                // Content
                if (selectedPartyCode == null) {
                    PartySelectionView(
                        currentPartyCode = currentPartyCode,
                        onPartySelected = { selectedPartyCode = it }
                    )
                } else {
                    selectedPartyCode?.let { partyCode ->
                        BudgetComparisonView(
                            currentPartyCode = currentPartyCode,
                            comparePartyCode = partyCode
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PartySelectionView(
    currentPartyCode: String,
    onPartySelected: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(standardPaddingMedium),
        verticalArrangement = Arrangement.spacedBy(standardPaddingMedium)
    ) {
        items(Party.entries.filter { it.code != currentPartyCode }) { party ->
            PartySelectionCard(
                partyCode = party.code,
                partyName = party.displayName,
                onClick = { onPartySelected(party.code) }
            )
        }
    }
}

@Composable
private fun PartySelectionCard(
    partyCode: String,
    partyName: String,
    onClick: () -> Unit
) {
    CommonCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(standardPaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Party color indicator
            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(8.dp),
                color = Party.getColorByCode(partyCode)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = partyCode,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Party info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = partyName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "Klicka för att jämföra budget",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Arrow indicator
            Text(
                text = "→",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun BudgetComparisonView(
    currentPartyCode: String,
    comparePartyCode: String
) {
    val currentPartyName = Party.fromCode(currentPartyCode)?.displayName ?: "Okänt parti"

    val comparePartyName = Party.fromCode(comparePartyCode)?.displayName ?: "Okänt parti"

    // Get budget data for both parties
    val currentBudgetData = remember(currentPartyCode) {
        SampleData.budgetCategories.sortedBy { it.displayOrder }.mapNotNull { category ->
            val partyBudget = category.partyBudgets.find { it.partyCode == currentPartyCode }
            if (partyBudget != null && partyBudget.budgetAmount > 0) {
                ComparisonBudgetItem(
                    partyCode = partyBudget.partyCode,
                    budgetAmount = partyBudget.budgetAmount,
                    sourceUrl = partyBudget.sourceUrl,
                    categoryName = category.name
                )
            } else null
        }
    }

    val compareBudgetData = remember(comparePartyCode) {
        SampleData.budgetCategories.sortedBy { it.displayOrder }.mapNotNull { category ->
            val partyBudget = category.partyBudgets.find { it.partyCode == comparePartyCode }
            if (partyBudget != null && partyBudget.budgetAmount > 0) {
                ComparisonBudgetItem(
                    partyCode = partyBudget.partyCode,
                    budgetAmount = partyBudget.budgetAmount,
                    sourceUrl = partyBudget.sourceUrl,
                    categoryName = category.name
                )
            } else null
        }
    }

    // Create comparison data
    val comparisonData = remember(currentBudgetData, compareBudgetData) {
        val allCategories = (currentBudgetData.map { it.categoryName } + compareBudgetData.map { it.categoryName }).distinct()
        allCategories.mapNotNull { categoryName ->
            val currentAmount = currentBudgetData.find { it.categoryName == categoryName }?.budgetAmount ?: 0.0
            val compareAmount = compareBudgetData.find { it.categoryName == categoryName }?.budgetAmount ?: 0.0

            if (currentAmount > 0 || compareAmount > 0) {
                ComparisonItem(
                    categoryName = categoryName,
                    currentAmount = currentAmount,
                    compareAmount = compareAmount
                )
            } else null
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = currentPartyName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Party.getColorByCode(currentPartyCode)
                )

                Text(
                    text = comparePartyName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Party.getColorByCode(comparePartyCode)
                )
            }
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        // Comparison items
        items(comparisonData) { item ->
            ComparisonItemCard(
                item = item,
                currentPartyColor = Party.getColorByCode(currentPartyCode),
                comparePartyColor = Party.getColorByCode(comparePartyCode)
            )
        }
    }
}

@Composable
private fun ComparisonItemCard(
    item: ComparisonItem,
    currentPartyColor: androidx.compose.ui.graphics.Color,
    comparePartyColor: androidx.compose.ui.graphics.Color
) {
    CommonCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.categoryName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Current party amount
                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "${item.currentAmount.toInt()} miljarder kr",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = currentPartyColor
                    )
                }

                // Compare party amount
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "${item.compareAmount.toInt()} miljarder kr",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = comparePartyColor
                    )
                }
            }

            // Difference
            val difference = item.currentAmount - item.compareAmount
            if (difference != 0.0) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (difference > 0) {
                        "+${difference.toInt()} miljarder kr skillnad"
                    } else {
                        "${difference.toInt()} miljarder kr skillnad"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = if (difference > 0) currentPartyColor else comparePartyColor
                )
            }
        }
    }
}

private data class ComparisonBudgetItem(
    val partyCode: String,
    val budgetAmount: Double,
    val sourceUrl: String,
    val categoryName: String
)

private data class ComparisonItem(
    val categoryName: String,
    val currentAmount: Double,
    val compareAmount: Double
)
