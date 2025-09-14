package se.atte.partier.screens

// Icons removed for now - will add back when needed
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import partier.composeapp.generated.resources.Res
import partier.composeapp.generated.resources.billion_kr
import partier.composeapp.generated.resources.nav_back
import partier.composeapp.generated.resources.parties_budget_proposals
import se.atte.partier.components.CommonCard
import se.atte.partier.components.CommonItemWithoutPadding
import se.atte.partier.components.HeroIcons
import se.atte.partier.components.painter
import se.atte.partier.components.standardPaddingMedium
import se.atte.partier.components.standardPaddingSmall
import se.atte.partier.constants.Party
import se.atte.partier.data.BudgetCategory
import se.atte.partier.data.PartyBudget
import se.atte.partier.data.SampleData
import se.atte.partier.data.Subcategory
import se.atte.partier.theme.ThemePreview
import se.atte.partier.utils.openUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(
    modifier: Modifier = Modifier,
    category: BudgetCategory,
    onBackClick: () -> Unit,
    partyBudgetLoader: (String) -> List<PartyBudget> = { categoryId -> SampleData.getPartyBudgetsForCategory(categoryId) }
) {
    var expandedPartyCode by remember { mutableStateOf<String?>(null) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
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
            val partyBudgets = remember(category.id) {
                partyBudgetLoader(category.id)
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
                    category = category,
                    expandedPartyCode = expandedPartyCode,
                    onPartyClick = { partyCode ->
                        expandedPartyCode = if (expandedPartyCode == partyCode) null else partyCode
                    }
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
    category: BudgetCategory,
    expandedPartyCode: String?,
    onPartyClick: (String) -> Unit
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
                category = category,
                isExpanded = expandedPartyCode == partyBudget.partyCode,
                onPartyClick = { onPartyClick(partyBudget.partyCode) }
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
    category: BudgetCategory,
    isExpanded: Boolean,
    onPartyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Use absolute values instead of normalized to ensure all bars are visible
    val normalizedValue = if (maxBudget > 0) {
        kotlin.math.abs(partyBudget.budgetAmount) / maxBudget
    } else 1.0

    CommonCard(
        modifier = modifier,
        onClick = if (category.subcategories.isNotEmpty()) onPartyClick else null
    ) {
        Column(
            modifier = Modifier.padding(standardPaddingMedium)
        ) {
            // Party name and amount using CommonItem
            CommonItemWithoutPadding(
                title = Party.fromCode(partyBudget.partyCode)?.displayName ?: partyBudget.partyCode,
                value = "${(partyBudget.budgetAmount * 10).toInt() / 10.0} ${stringResource(Res.string.billion_kr)}",
                showDivider = false,
                endContent = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Link,
                            contentDescription = "Öppna källa",
                            modifier = Modifier
                                .clickable { openUrl(partyBudget.sourceUrl) }
                                .padding(8.dp)
                                .size(22.dp),
                            tint = MaterialTheme.colorScheme.primary,
                        )
                        // Expand/collapse icon
                        if (category.subcategories.isNotEmpty()) {
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = if (isExpanded) "Dölj underkategorier" else "Visa underkategorier",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(22.dp),
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                }
            )

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

            // Subcategories (expandable)
            AnimatedVisibility(
                visible = isExpanded && category.subcategories.isNotEmpty(),
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                SubcategoryBarChart(
                    modifier = Modifier.fillMaxWidth(),
                    subcategories = category.subcategories,
                    partyCode = partyBudget.partyCode,
                    color = color,
                    mainCategoryTotal = kotlin.math.abs(partyBudget.budgetAmount)
                )
            }
        }
    }
}

@Composable
private fun SubcategoryBarChart(
    modifier: Modifier = Modifier,
    subcategories: List<Subcategory>,
    partyCode: String,
    color: Color,
    mainCategoryTotal: Double
) {
    if (subcategories.isEmpty()) return

    Column(
        modifier = modifier.padding(top = standardPaddingSmall)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "Underkategorier",
        )

        subcategories.forEachIndexed { index, subcategory ->
            val partyBudget = subcategory.partyBudgets.find { it.partyCode == partyCode }
            if (partyBudget != null) {
                SubcategoryBarItem(
                    modifier = Modifier.fillMaxWidth(),
                    subcategory = subcategory,
                    partyBudget = partyBudget,
                    color = color,
                    mainCategoryTotal = mainCategoryTotal
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
private fun SubcategoryBarItem(
    modifier: Modifier = Modifier,
    subcategory: Subcategory,
    partyBudget: PartyBudget,
    color: Color,
    mainCategoryTotal: Double
) {
    val normalizedValue = if (mainCategoryTotal > 0) {
        kotlin.math.abs(partyBudget.budgetAmount) / mainCategoryTotal
    } else 1.0

    Row(
        modifier = modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Subcategory name (smaller text)
        Text(
            modifier = Modifier.weight(1f),
            text = subcategory.name,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Amount in kr
        Text(
            modifier = Modifier,
            text = "${(kotlin.math.round(partyBudget.budgetAmount * 100.0) / 100.0)} ${stringResource(Res.string.billion_kr)}",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Percentage text - percentage of main category (0-100%)
        Text(
            modifier = Modifier,
            text = "${(normalizedValue * 100).toInt()}%",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Mini bar chart - 1/5 of total width
        Box(
            modifier = Modifier
                .width(60.dp) // 1/5 of typical screen width (300dp)
                .height(8.dp)
        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                // Bar width should be percentage of main category (0-100%)
                val barWidth = size.width * normalizedValue.toFloat()
                val barHeight = size.height

                // Background bar (light gray)
                drawRect(
                    color = Color.Gray.copy(alpha = 0.2f),
                    topLeft = Offset.Zero,
                    size = Size(size.width, barHeight)
                )

                // Main bar - filled to percentage of main category
                drawRect(
                    color = color.copy(alpha = 0.7f),
                    topLeft = Offset.Zero,
                    size = Size(barWidth, barHeight)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Source link icon (smaller)
        Icon(
            modifier = Modifier
                .clickable { openUrl(partyBudget.sourceUrl) }
                .padding(4.dp)
                .size(14.dp),
            imageVector = Icons.Default.Link,
            contentDescription = "Öppna källa"
        )
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
                modifier = Modifier,
                text = "Inga budgetförslag tillgängliga för denna kategori ännu",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier,
                text = "Data kommer snart när partierna publicerar sina budgetförslag",
                style = MaterialTheme.typography.bodyMedium
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
        val mockCategory = BudgetCategory(
            id = "healthcare",
            name = "Hälsovård, sjukvård och social omsorg",
            description = "Budget för hälsovård och sjukvård",
            displayOrder = 1,
            partyBudgets = emptyList(), // Will be loaded by partyBudgetLoader
            subcategories = listOf(
                Subcategory(
                    code = "1:1",
                    name = "Primärvård och specialiserad vård",
                    partyBudgets = listOf(
                        PartyBudget(partyCode = "S", budgetAmount = 120.5, sourceUrl = "https://example.com/s"),
                        PartyBudget(partyCode = "M", budgetAmount = 110.0, sourceUrl = "https://example.com/m"),
                        PartyBudget(partyCode = "SD", budgetAmount = 130.2, sourceUrl = "https://example.com/sd"),
                        PartyBudget(partyCode = "V", budgetAmount = 125.8, sourceUrl = "https://example.com/v")
                    )
                ),
                Subcategory(
                    code = "1:2",
                    name = "Social omsorg och äldreomsorg",
                    partyBudgets = listOf(
                        PartyBudget(partyCode = "S", budgetAmount = 200.0, sourceUrl = "https://example.com/s"),
                        PartyBudget(partyCode = "M", budgetAmount = 180.0, sourceUrl = "https://example.com/m"),
                        PartyBudget(partyCode = "SD", budgetAmount = 220.0, sourceUrl = "https://example.com/sd"),
                        PartyBudget(partyCode = "V", budgetAmount = 210.0, sourceUrl = "https://example.com/v")
                    )
                )
            )
        )
        val mockPartyBudgets = listOf(
            PartyBudget(partyCode = "S", budgetAmount = 450.5, sourceUrl = "https://example.com/s"),
            PartyBudget(partyCode = "M", budgetAmount = 420.0, sourceUrl = "https://example.com/m"),
            PartyBudget(partyCode = "SD", budgetAmount = 480.2, sourceUrl = "https://example.com/sd"),
            PartyBudget(partyCode = "V", budgetAmount = 460.8, sourceUrl = "https://example.com/v")
        )
        CategoryDetailScreen(
            modifier = Modifier.fillMaxSize(),
            category = mockCategory,
            onBackClick = {},
            partyBudgetLoader = { mockPartyBudgets }
        )
    }
}

@Preview
@Composable
fun PreviewCategoryDetailScreen_Dark() {
    ThemePreview(useDarkMode = true) {
        val mockCategory = BudgetCategory(
            id = "education",
            name = "Utbildning och universitetsforskning",
            description = "Budget för utbildning och forskning",
            displayOrder = 2,
            partyBudgets = emptyList(), // Will be loaded by partyBudgetLoader
            subcategories = listOf(
                Subcategory(
                    code = "2:1",
                    name = "Grundskola och gymnasieskola",
                    partyBudgets = listOf(
                        PartyBudget(partyCode = "S", budgetAmount = 150.0, sourceUrl = "https://example.com/s"),
                        PartyBudget(partyCode = "M", budgetAmount = 140.0, sourceUrl = "https://example.com/m"),
                        PartyBudget(partyCode = "SD", budgetAmount = 160.0, sourceUrl = "https://example.com/sd"),
                        PartyBudget(partyCode = "V", budgetAmount = 155.0, sourceUrl = "https://example.com/v")
                    )
                ),
                Subcategory(
                    code = "2:2",
                    name = "Högskola och universitet",
                    partyBudgets = listOf(
                        PartyBudget(partyCode = "S", budgetAmount = 100.0, sourceUrl = "https://example.com/s"),
                        PartyBudget(partyCode = "M", budgetAmount = 95.0, sourceUrl = "https://example.com/m"),
                        PartyBudget(partyCode = "SD", budgetAmount = 110.0, sourceUrl = "https://example.com/sd"),
                        PartyBudget(partyCode = "V", budgetAmount = 105.0, sourceUrl = "https://example.com/v")
                    )
                )
            )
        )
        val mockPartyBudgets = listOf(
            PartyBudget(partyCode = "S", budgetAmount = 380.2, sourceUrl = "https://example.com/s"),
            PartyBudget(partyCode = "M", budgetAmount = 350.8, sourceUrl = "https://example.com/m"),
            PartyBudget(partyCode = "SD", budgetAmount = 400.1, sourceUrl = "https://example.com/sd"),
            PartyBudget(partyCode = "V", budgetAmount = 420.1, sourceUrl = "https://example.com/v")
        )
        CategoryDetailScreen(
            modifier = Modifier.fillMaxSize(),
            category = mockCategory,
            onBackClick = {},
            partyBudgetLoader = { mockPartyBudgets }
        )
    }
}

@Preview
@Composable
fun PreviewEmptyCategoryDetailScreen() {
    ThemePreview {
        EmptyScreen(modifier = Modifier.fillMaxWidth())
    }
}