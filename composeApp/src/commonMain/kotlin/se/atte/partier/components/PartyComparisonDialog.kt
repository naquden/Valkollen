package se.atte.partier.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.atte.partier.constants.Party
import se.atte.partier.data.SampleData
import se.atte.partier.data.Subcategory
import se.atte.partier.theme.ThemePreview

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
                                Icon(
                                    painter = HeroIcons.ArrowBack.painter(),
                                    contentDescription = "Tillbaka"
                                )
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
        verticalArrangement = Arrangement.spacedBy(standardPaddingSmall)
    ) {
        items(Party.entries.filter { it.code != currentPartyCode }) { party ->
            PartySelectionCard(
                partyCode = party.code,
                partyName = party.displayName,
                onClick = { onPartySelected(party.code) }
            )
        }

        item { Spacer(modifier = Modifier.height(standardPaddingSmall)) }
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
            .clickable { onClick() }
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
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(standardPaddingMedium))

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
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Arrow indicator
            Icon(
                painter = HeroIcons.ArrowRight.painter(),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun BudgetComparisonView(
    currentPartyCode: String,
    comparePartyCode: String
) {
    var expandedCategory by remember { mutableStateOf<String?>(null) }
    
    val currentPartyName = Party.fromCode(currentPartyCode)?.displayName ?: "Okänt parti"
    val comparePartyName = Party.fromCode(comparePartyCode)?.displayName ?: "Okänt parti"

    // Get budget data for both parties with categories
    val budgetCategories = remember {
        SampleData.budgetCategories.sortedBy { it.displayOrder }
    }

    // Create comparison data with categories
    val comparisonData = remember(budgetCategories, currentPartyCode, comparePartyCode) {
        budgetCategories.mapNotNull { category ->
            val currentBudget = category.partyBudgets.find { it.partyCode == currentPartyCode }
            val compareBudget = category.partyBudgets.find { it.partyCode == comparePartyCode }
            
            if (currentBudget != null && currentBudget.budgetAmount > 0 || 
                compareBudget != null && compareBudget.budgetAmount > 0) {
                ComparisonCategoryItem(
                    category = category,
                    currentAmount = currentBudget?.budgetAmount ?: 0.0,
                    compareAmount = compareBudget?.budgetAmount ?: 0.0,
                    currentSourceUrl = currentBudget?.sourceUrl,
                    compareSourceUrl = compareBudget?.sourceUrl
                )
            } else null
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(standardPaddingMedium),
        verticalArrangement = Arrangement.spacedBy(standardPaddingSmall)
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
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = comparePartyName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        item { Spacer(modifier = Modifier.height(standardPaddingSmall)) }

        // Comparison items
        items(comparisonData) { item ->
            ComparisonCategoryCard(
                item = item,
                currentPartyCode = currentPartyCode,
                comparePartyCode = comparePartyCode,
                isExpanded = expandedCategory == item.category.id,
                onCategoryClick = { 
                    expandedCategory = if (expandedCategory == item.category.id) null else item.category.id
                }
            )
        }
    }
}

@Composable
private fun ComparisonCategoryCard(
    item: ComparisonCategoryItem,
    currentPartyCode: String,
    comparePartyCode: String,
    isExpanded: Boolean,
    onCategoryClick: () -> Unit
) {
    val currentPartyColor = Party.getColorByCode(currentPartyCode)
    val comparePartyColor = Party.getColorByCode(comparePartyCode)
    
    CommonCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = if (item.category.subcategories.isNotEmpty()) onCategoryClick else null
    ) {
        Column(
            modifier = Modifier.padding(standardPaddingMedium)
        ) {
            // Category header using CommonItem
            CommonItemWithoutPadding(
                title = item.category.name,
                showDivider = false,
                endContent = {
                    // Expand/collapse icon
                    if (item.category.subcategories.isNotEmpty()) {
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = if (isExpanded) "Dölj underkategorier" else "Visa underkategorier",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(20.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )

            // Visual comparison bar
            ComparisonBar(
                currentAmount = item.currentAmount,
                compareAmount = item.compareAmount,
                currentPartyColor = currentPartyColor,
                comparePartyColor = comparePartyColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .padding(vertical = standardPaddingSmall)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Current party amount
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "${item.currentAmount.toInt()} miljarder kr",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Compare party amount
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "${item.compareAmount.toInt()} miljarder kr",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
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
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            // Subcategories (expandable)
            AnimatedVisibility(
                visible = isExpanded && item.category.subcategories.isNotEmpty(),
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                SubcategoryComparisonView(
                    modifier = Modifier.fillMaxWidth(),
                    subcategories = item.category.subcategories,
                    currentPartyCode = currentPartyCode,
                    comparePartyCode = comparePartyCode,
                    currentPartyColor = currentPartyColor,
                    comparePartyColor = comparePartyColor,
                    currentMainCategoryTotal = item.currentAmount,
                    compareMainCategoryTotal = item.compareAmount
                )
            }
        }
    }
}

@Composable
private fun SubcategoryComparisonView(
    modifier: Modifier = Modifier,
    subcategories: List<Subcategory>,
    currentPartyCode: String,
    comparePartyCode: String,
    currentPartyColor: Color,
    comparePartyColor: Color,
    currentMainCategoryTotal: Double,
    compareMainCategoryTotal: Double
) {
    if (subcategories.isEmpty()) return

    Column(
        modifier = modifier.padding(top = standardPaddingSmall)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "Underkategorier",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium
        )
        
        subcategories.forEachIndexed { index, subcategory ->
            val currentBudget = subcategory.partyBudgets.find { it.partyCode == currentPartyCode }
            val compareBudget = subcategory.partyBudgets.find { it.partyCode == comparePartyCode }
            
            if (currentBudget != null && currentBudget.budgetAmount > 0 || 
                compareBudget != null && compareBudget.budgetAmount > 0) {
                SubcategoryComparisonItem(
                    modifier = Modifier.fillMaxWidth(),
                    subcategory = subcategory,
                    currentAmount = currentBudget?.budgetAmount ?: 0.0,
                    compareAmount = compareBudget?.budgetAmount ?: 0.0,
                    currentPartyColor = currentPartyColor,
                    comparePartyColor = comparePartyColor,
                    currentMainCategoryTotal = currentMainCategoryTotal,
                    compareMainCategoryTotal = compareMainCategoryTotal
                )
                
                // Add divider between items (except after last item)
                if (index < subcategories.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 4.dp),
                        thickness = 0.5.dp
                    )
                }
            }
        }
    }
}

@Composable
private fun SubcategoryComparisonItem(
    modifier: Modifier = Modifier,
    subcategory: Subcategory,
    currentAmount: Double,
    compareAmount: Double,
    currentPartyColor: Color,
    comparePartyColor: Color,
    currentMainCategoryTotal: Double,
    compareMainCategoryTotal: Double
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        // Title row with party amounts on sides
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Current party amount (left side)
            Text(
                modifier = Modifier.weight(1f),
                text = "${(kotlin.math.round(currentAmount * 100.0) / 100.0)} miljarder kr",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start
            )

            // Centered subcategory title
            Text(
                modifier = Modifier.weight(2f),
                text = subcategory.name,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Compare party amount (right side)
            Text(
                modifier = Modifier.weight(1f),
                text = "${(kotlin.math.round(compareAmount * 100.0) / 100.0)} miljarder kr",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Proportional comparison bar (like main category comparison)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                val totalAmount = kotlin.math.abs(currentAmount) + kotlin.math.abs(compareAmount)
                val currentPercentage = if (totalAmount > 0) kotlin.math.abs(currentAmount) / totalAmount else 0.5
                val comparePercentage = if (totalAmount > 0) kotlin.math.abs(compareAmount) / totalAmount else 0.5

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

                // Current party bar (left side)
                val currentBarWidth = canvasWidth * currentPercentage.toFloat()
                drawRect(
                    color = currentPartyColor,
                    topLeft = Offset(0f, barY),
                    size = Size(currentBarWidth, barHeight)
                )

                // Compare party bar (right side)
                val compareBarWidth = canvasWidth * comparePercentage.toFloat()
                val compareBarX = canvasWidth - compareBarWidth
                drawRect(
                    color = comparePartyColor,
                    topLeft = Offset(compareBarX, barY),
                    size = Size(compareBarWidth, barHeight)
                )

                // Center divider line (when amounts are equal)
                if (kotlin.math.abs(currentAmount - compareAmount) < 0.001 && kotlin.math.abs(currentAmount) > 0) {
                    val centerX = canvasWidth / 2f
                    drawLine(
                        color = Color.White,
                        start = Offset(centerX, barY),
                        end = Offset(centerX, barY + barHeight),
                        strokeWidth = 2.dp.toPx()
                    )
                }
            }
        }
    }
}

@Composable
private fun ComparisonBar(
    currentAmount: Double,
    compareAmount: Double,
    currentPartyColor: Color,
    comparePartyColor: Color,
    modifier: Modifier = Modifier
) {
    val totalAmount = currentAmount + compareAmount
    val currentPercentage = if (totalAmount > 0) currentAmount / totalAmount else 0.5
    val comparePercentage = if (totalAmount > 0) compareAmount / totalAmount else 0.5

    Canvas(modifier = modifier) {
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

        // Current party bar (left side)
        val currentBarWidth = canvasWidth * currentPercentage.toFloat()
        drawRect(
            color = currentPartyColor,
            topLeft = Offset(0f, barY),
            size = Size(currentBarWidth, barHeight)
        )

        // Compare party bar (right side)
        val compareBarWidth = canvasWidth * comparePercentage.toFloat()
        val compareBarX = canvasWidth - compareBarWidth
        drawRect(
            color = comparePartyColor,
            topLeft = Offset(compareBarX, barY),
            size = Size(compareBarWidth, barHeight)
        )

        // Center divider line (when amounts are equal)
        if (currentAmount == compareAmount && currentAmount > 0) {
            val centerX = canvasWidth / 2f
            drawLine(
                color = Color.White,
                start = Offset(centerX, barY),
                end = Offset(centerX, barY + barHeight),
                strokeWidth = 2.dp.toPx()
            )
        }
    }
}

private data class ComparisonBudgetItem(
    val partyCode: String,
    val budgetAmount: Double,
    val sourceUrl: String,
    val categoryName: String
)

@Preview
@Composable
private fun PreviewPartyComparisonDialog() {
    ThemePreview {
        PartyComparisonDialog(
            currentPartyCode = "SD",
            onDismiss = {}
        )
    }
}

@Preview
@Composable
private fun PreviewPartyComparisonDialog_Dark() {
    ThemePreview(useDarkMode = true) {
        PartyComparisonDialog(
            currentPartyCode = "M",
            onDismiss = {}
        )
    }
}

@Preview
@Composable
private fun PreviewComparisonView() {
    ThemePreview {
        BudgetComparisonView(
            currentPartyCode = "SD",
            comparePartyCode = "M"
        )
    }
}

@Preview
@Composable
private fun PreviewComparisonView_Dark() {
    ThemePreview(useDarkMode = true) {
        BudgetComparisonView(
            currentPartyCode = "M",
            comparePartyCode = "MP"
        )
    }
}

private data class ComparisonItem(
    val categoryName: String,
    val currentAmount: Double,
    val compareAmount: Double
)

data class ComparisonCategoryItem(
    val category: se.atte.partier.data.BudgetCategory,
    val currentAmount: Double,
    val compareAmount: Double,
    val currentSourceUrl: String?,
    val compareSourceUrl: String?
)
