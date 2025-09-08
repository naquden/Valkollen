package se.atte.partier.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.*

@Composable
fun BudgetPieChart(
    data: List<PieChartData>,
    modifier: Modifier = Modifier,
    title: String = "Budgetfördelning"
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Pie Chart
                Canvas(
                    modifier = Modifier
                        .size(200.dp)
                        .weight(1f)
                ) {
                    drawPieChart(data)
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                // Legend
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(data) { item ->
                        LegendItem(
                            color = item.color,
                            label = item.label,
                            value = item.value,
                            percentage = item.percentage
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LegendItem(
    color: Color,
    label: String,
    value: Double,
    percentage: Double
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(12.dp),
            shape = androidx.compose.foundation.shape.CircleShape,
            color = color
        ) {}
        
        Spacer(modifier = Modifier.width(8.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            
            Text(
                text = "${value.toInt()} miljarder kr (${percentage.toInt()}%)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun DrawScope.drawPieChart(data: List<PieChartData>) {
    if (data.isEmpty()) return
    val total = data.sumOf { it.value }
    if (total <= 0) return
    
    val center = Offset(size.width / 2f, size.height / 2f)
    val radius = maxOf(10f, minOf(size.width, size.height) / 2f - 20f)
    val strokeWidth = 4.dp.toPx()
    
    var startAngle = -90f // Start from top
    
    data.forEach { item ->
        val sweepAngle = ((item.value / total) * 360f).toFloat()
        
        drawArc(
            color = item.color,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = true,
            topLeft = Offset(
                center.x - radius,
                center.y - radius
            ),
            size = Size(radius * 2, radius * 2)
        )
        
        // Draw border
        drawArc(
            color = Color.White,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = true,
            topLeft = Offset(
                center.x - radius,
                center.y - radius
            ),
            size = Size(radius * 2, radius * 2),
            style = Stroke(width = strokeWidth)
        )
        
        startAngle += sweepAngle.toFloat()
    }
}

data class PieChartData(
    val label: String,
    val value: Double,
    val color: Color,
    val percentage: Double
)
