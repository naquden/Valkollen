package se.atte.partier.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import partier.composeapp.generated.resources.Res
import partier.composeapp.generated.resources.budget_proposal
import partier.composeapp.generated.resources.billion_kr
import se.atte.partier.utils.openUrl
import se.atte.partier.data.PartyBudget

@Composable
fun BudgetBarChart(
    partyBudgets: List<PartyBudget>,
    modifier: Modifier = Modifier
) {
    if (partyBudgets.isEmpty()) return
    
    val maxBudget = partyBudgets.maxOfOrNull { it.budgetAmount } ?: 0.0
    val minBudget = partyBudgets.minOfOrNull { it.budgetAmount } ?: 0.0
    val budgetRange = maxBudget - minBudget
    
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(partyBudgets) { index, partyBudget ->
            BarChartItem(
                partyBudget = partyBudget,
                maxBudget = maxBudget,
                minBudget = minBudget,
                budgetRange = budgetRange,
                color = getPartyColor(partyBudget.partyName),
                modifier = Modifier.fillMaxWidth()
            )
        }
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
    val normalizedValue = if (budgetRange > 0) {
        (partyBudget.budgetAmount - minBudget) / budgetRange
    } else 1.0
    
    Card(
        modifier = modifier
            .clickable { openUrl(partyBudget.sourceUrl) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Party name and amount
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = partyBudget.partyName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = stringResource(Res.string.budget_proposal) + " (Klicka för källa)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
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
                    Text(
                        text = "🔗",
                        style = MaterialTheme.typography.bodySmall
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

private fun getPartyColor(partyName: String): Color {
    return when {
        partyName.contains("Moderaterna") -> Color(0xFF1E3A8A) // Dark Blue
        partyName.contains("Kristdemokraterna") -> Color(0xFF3B82F6) // Blue
        partyName.contains("Liberalerna") -> Color(0xFF60A5FA) // Light Blue
        partyName.contains("Centerpartiet") -> Color(0xFF10B981) // Green
        partyName.contains("Miljöpartiet") -> Color(0xFF059669) // Dark Green
        partyName.contains("Vänsterpartiet") -> Color(0xFFDC2626) // Red
        partyName.contains("Socialdemokraterna") -> Color(0xFFEF4444) // Light Red
        partyName.contains("Sverigedemokraterna") -> Color(0xFFF59E0B) // Yellow/Orange
        else -> Color(0xFF6B7280) // Gray fallback
    }
}
