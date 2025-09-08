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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.atte.partier.utils.openUrl
import se.atte.partier.data.IncomeType

@Composable
fun IncomeBarChart(
    incomeTypes: List<IncomeType>,
    modifier: Modifier = Modifier
) {
    if (incomeTypes.isEmpty()) return
    
    val maxAmount = incomeTypes.maxOfOrNull { kotlin.math.abs(it.amount) } ?: 0.0
    
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(incomeTypes) { index, incomeType ->
            IncomeBarChartItem(
                incomeType = incomeType,
                maxAmount = maxAmount,
                color = getIncomeTypeColor(incomeType.id),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun IncomeBarChartItem(
    incomeType: IncomeType,
    maxAmount: Double,
    color: Color,
    modifier: Modifier = Modifier
) {
    val normalizedValue = if (maxAmount > 0) {
        kotlin.math.abs(incomeType.amount) / maxAmount
    } else 1.0
    
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Income type name and amount
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = incomeType.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = incomeType.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "${if (incomeType.amount >= 0) "+" else ""}${(incomeType.amount * 10).toInt() / 10.0} miljarder kr",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (incomeType.amount >= 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "(${if (incomeType.percentage >= 0) "+" else ""}${(incomeType.percentage * 10).toInt() / 10.0}%)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
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
                }
            }
        }
    }
}

private fun getIncomeTypeColor(incomeTypeId: String): Color {
    return when (incomeTypeId) {
        "tax_income" -> Color(0xFF1E40AF) // Blue
        "activity_income" -> Color(0xFF059669) // Green
        "property_sales" -> Color(0xFF7C3AED) // Purple
        "loan_repayments" -> Color(0xFF0891B2) // Cyan
        "calculated_income" -> Color(0xFFCA8A04) // Yellow
        "eu_grants" -> Color(0xFFDC2626) // Red
        "tax_system_adjustments" -> Color(0xFFEF4444) // Light Red
        else -> Color(0xFF6B7280) // Gray fallback
    }
}

