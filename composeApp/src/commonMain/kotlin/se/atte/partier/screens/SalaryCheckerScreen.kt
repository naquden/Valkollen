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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.atte.partier.components.HeroIcons
import se.atte.partier.components.painter
import se.atte.partier.components.standardPaddingLarge
import se.atte.partier.components.standardPaddingMedium
import se.atte.partier.components.standardPaddingSmall
import se.atte.partier.constants.Party
import se.atte.partier.data.PartyTaxCalculation
import se.atte.partier.data.TaxData
import se.atte.partier.data.TaxDataLoader
import se.atte.partier.data.PartyTaxProposal
import se.atte.partier.data.TaxProposalDetails
import se.atte.partier.data.IncomeTaxDetails
import se.atte.partier.data.DeductionDetails
import se.atte.partier.data.AdditionalTaxes
import se.atte.partier.data.CalculationMethod
import se.atte.partier.theme.ThemePreview
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalaryCheckerScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    var salary by remember { mutableStateOf(50000f) } // Default 50,000 SEK
    var isOver66 by remember { mutableStateOf(false) } // Default under 66 years old
    var taxData by remember { mutableStateOf<TaxData?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    
    // Load tax data when screen is first displayed
    LaunchedEffect(Unit) {
        try {
            // Load tax data from JSON file
            taxData = TaxDataLoader.loadTaxData()
            isLoading = false
        } catch (e: Exception) {
            // Fallback to basic data if loading fails
            isLoading = false
        }
    }
    
    // Calculate salary comparisons for all parties
    val salaryComparisons = remember(salary, isOver66, taxData) {
        if (taxData != null) {
            val age = if (isOver66) 70 else 30 // Convert to age for calculation
            TaxDataLoader.calculatePartyTaxes(salary.toDouble(), taxData!!, age)
        } else {
            emptyList()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lön kollen",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                navigationIcon = {
                    androidx.compose.material3.TextButton(onClick = onBackClick) {
                        androidx.compose.material3.Icon(
                            painter = HeroIcons.ArrowBack.painter(),
                            contentDescription = "Tillbaka"
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        if (isLoading) {
            // Loading state
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(standardPaddingMedium)
                ) {
                    Text(
                        text = "Laddar skattedata...",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Hämtar de senaste skatteförslagen från partierna",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .padding(horizontal = standardPaddingMedium),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                item {
                    // Salary input section
                    SalaryInputCard(
                        salary = salary,
                        isOver66 = isOver66,
                        onSalaryChange = { salary = it },
                        onAgeChange = { isOver66 = it }
                    )
                }
                
                item {
                    // Party comparison section
                    Text(
                        text = "Partiernas skatteförslag",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                
                items(salaryComparisons) { comparison ->
                    PartySalaryBar(
                        comparison = comparison,
                        maxNetSalary = salaryComparisons.maxOfOrNull { it.netSalary } ?: 0.0
                    )
                }
                
                item { 
                    Spacer(modifier = Modifier.height(standardPaddingLarge)) 
                }
            }
        }
    }
}

@Composable
private fun SalaryInputCard(
    salary: Float,
    isOver66: Boolean,
    onSalaryChange: (Float) -> Unit,
    onAgeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(standardPaddingMedium)
        ) {
            Text(
                text = "Din bruttolön per månad",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Spacer(modifier = Modifier.height(standardPaddingSmall))
            
            Text(
                text = "Ange din bruttolön (före skatt och avdrag)",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(standardPaddingSmall))
            
            Text(
                text = "Dra i reglaget för att ändra din lön",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(standardPaddingMedium))
            
            // Salary display
            Text(
                text = "${salary.roundToInt().toString().replace(Regex("(\\d)(?=(\\d{3})+$)"), "$1 ")} kr",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(standardPaddingMedium))
            
            // Slider
            Slider(
                value = salary,
                onValueChange = onSalaryChange,
                valueRange = 20000f..100000f, // 20,000 to 100,000 SEK
                modifier = Modifier.fillMaxWidth()
            )
            
            // Min/Max labels
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "20 000 kr",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "100 000 kr",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.height(standardPaddingMedium))
            
            // Age checkbox section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isOver66,
                    onCheckedChange = onAgeChange,
                    modifier = Modifier.padding(end = standardPaddingSmall)
                )
                
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Jag är 66 år eller äldre",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Text(
                        text = if (isOver66) {
                            "Brytpunkt för statlig skatt: 733 200 kr/år (högre grundavdrag)"
                        } else {
                            "Brytpunkt för statlig skatt: 643 100 kr/år"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
private fun PartySalaryBar(
    comparison: PartyTaxCalculation,
    maxNetSalary: Double,
    modifier: Modifier = Modifier
) {
    val partyColor = Party.getColorByCode(comparison.partyCode)
    val normalizedValue = if (maxNetSalary > 0) {
        comparison.netSalary / maxNetSalary
    } else 1.0

    // Just the bar with party name and amount inside
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
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
                color = partyColor,
                topLeft = Offset.Zero,
                size = Size(barWidth, barHeight)
            )
        }
        
        // Party name and amount inside the bar
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = comparison.partyName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            
            Text(
                text = "${comparison.netSalary.roundToInt().toString().replace(Regex("(\\d)(?=(\\d{3})+$)"), "$1 ")} kr",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewSalaryCheckerScreen() {
    ThemePreview {
        SalaryCheckerScreen(
            onBackClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewSalaryCheckerScreen_Dark() {
    ThemePreview(useDarkMode = true) {
        SalaryCheckerScreen(
            onBackClick = {}
        )
    }
}