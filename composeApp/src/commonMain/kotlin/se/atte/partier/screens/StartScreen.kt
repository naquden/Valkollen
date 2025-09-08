package se.atte.partier.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import partier.composeapp.generated.resources.Res
import partier.composeapp.generated.resources.budget_title
import partier.composeapp.generated.resources.budget_subtitle
import se.atte.partier.data.SampleData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    onViewSelected: (ViewType) -> Unit
) {
    val year = remember { SampleData.year }
    val totalBudget = remember { SampleData.totalBudget }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Sveriges Budget $year",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text(
                text = "${totalBudget.toInt()} miljarder kronor fördelas över olika områden",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // View options
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(ViewType.values()) { viewType ->
                ViewOptionCard(
                    viewType = viewType,
                    onClick = { onViewSelected(viewType) }
                )
            }
        }
    }
}

@Composable
private fun ViewOptionCard(
    viewType: ViewType,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = viewType.icon,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    text = viewType.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Text(
                text = viewType.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

enum class ViewType(
    val icon: String,
    val title: String,
    val description: String
) {
    EXPENSES(
        icon = "💰",
        title = "Utgifter",
        description = "Se statens utgifter fördelade på olika områden och partiernas budgetförslag"
    ),
    INCOME(
        icon = "📈",
        title = "Inkomster",
        description = "Se statens inkomster från skatter, avgifter och andra källor"
    ),
    BY_PARTY(
        icon = "🏛️",
        title = "Specifik parti",
        description = "Se varje partis budgetförslag i detalj"
    )
}

