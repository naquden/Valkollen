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
    var taxData by remember { mutableStateOf<TaxData?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    
    // Load tax data when screen is first displayed
    LaunchedEffect(Unit) {
        try {
            // For now, use hardcoded tax data to test the calculation logic
            taxData = createHardcodedTaxData()
            isLoading = false
        } catch (e: Exception) {
            // Fallback to hardcoded data if loading fails
            isLoading = false
        }
    }
    
    // Calculate salary comparisons for all parties
    val salaryComparisons = remember(salary, taxData) {
        if (taxData != null) {
            TaxDataLoader.calculatePartyTaxes(salary.toDouble(), taxData!!)
        } else {
            // Fallback to hardcoded data
            Party.entries.map { party ->
                PartyTaxCalculation(
                    partyCode = party.code,
                    partyName = party.displayName,
                    grossSalary = salary.toDouble(),
                    totalTaxRate = 0.33,
                    jobbskatteavdrag = 0.0,
                    grundavdrag = 0,
                    netSalary = salary.toDouble() * 0.67,
                    taxAmount = salary.toDouble() * 0.33,
                    effectiveTaxRate = 0.33
                )
            }.sortedByDescending { it.netSalary }
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
                        onSalaryChange = { salary = it }
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
                        maxNetSalary = salaryComparisons.first().netSalary
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
    onSalaryChange: (Float) -> Unit,
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
                text = "Din månadslön",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Spacer(modifier = Modifier.height(standardPaddingSmall))
            
            Text(
                text = "Dra i reglaget för att ändra din lön",
                style = MaterialTheme.typography.bodyMedium,
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

private fun createHardcodedTaxData(): TaxData {
    return TaxData(
        year = 2025,
        description = "Hardcoded tax data for testing",
        parties = listOf(
            PartyTaxProposal(
                partyCode = "S",
                partyName = "Socialdemokraterna (S)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.32,
                        municipalTaxRate = 0.32,
                        totalTaxRate = 0.32,
                        description = "Balanserad skattepolitik med fokus på välfärd"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.10,
                        grundavdrag = 20000,
                        description = "Förbättrat jobbskatteavdrag och grundavdrag"
                    )
                )
            ),
            PartyTaxProposal(
                partyCode = "M",
                partyName = "Moderaterna (M)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.30,
                        municipalTaxRate = 0.30,
                        totalTaxRate = 0.30,
                        description = "Sänkta skatter för att stimulera arbete och entreprenörskap"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.12,
                        grundavdrag = 25000,
                        description = "Utökat jobbskatteavdrag och högre grundavdrag"
                    )
                )
            ),
            PartyTaxProposal(
                partyCode = "SD",
                partyName = "Sverigedemokraterna (SD)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.31,
                        municipalTaxRate = 0.31,
                        totalTaxRate = 0.31,
                        description = "Balanserad skattepolitik med fokus på arbetarklassen"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.11,
                        grundavdrag = 22000,
                        description = "Förbättrat jobbskatteavdrag för arbetare"
                    )
                )
            ),
            PartyTaxProposal(
                partyCode = "V",
                partyName = "Vänsterpartiet (V)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.35,
                        municipalTaxRate = 0.35,
                        totalTaxRate = 0.35,
                        description = "Progressiv skatteskala med högre skatter för rika"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.08,
                        grundavdrag = 18000,
                        description = "Fokus på offentliga investeringar framför skattelättnader"
                    )
                )
            ),
            PartyTaxProposal(
                partyCode = "C",
                partyName = "Centerpartiet (C)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.29,
                        municipalTaxRate = 0.29,
                        totalTaxRate = 0.29,
                        description = "Grön skatteomställning med sänkta skatter"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.13,
                        grundavdrag = 28000,
                        description = "Miljöinriktade skattelättnader och högre grundavdrag"
                    )
                )
            ),
            PartyTaxProposal(
                partyCode = "L",
                partyName = "Liberalerna (L)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.25,
                        municipalTaxRate = 0.25,
                        totalTaxRate = 0.25,
                        description = "Liberala skattesänkningar för att främja tillväxt"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.15,
                        grundavdrag = 30000,
                        description = "Maximalt jobbskatteavdrag och höga grundavdrag"
                    )
                )
            ),
            PartyTaxProposal(
                partyCode = "KD",
                partyName = "Kristdemokraterna (KD)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.30,
                        municipalTaxRate = 0.30,
                        totalTaxRate = 0.30,
                        description = "Familjevänlig skattepolitik med fokus på barnfamiljer"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.11,
                        grundavdrag = 24000,
                        description = "Familjeinriktade skattelättnader och förbättrat jobbskatteavdrag"
                    )
                )
            ),
            PartyTaxProposal(
                partyCode = "MP",
                partyName = "Miljöpartiet (MP)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.38,
                        municipalTaxRate = 0.38,
                        totalTaxRate = 0.38,
                        description = "Grön skatteomställning med miljöskatter"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.09,
                        grundavdrag = 19000,
                        description = "Miljöinriktade skattelättnader för gröna jobb"
                    )
                )
            )
        ),
        calculationMethod = CalculationMethod(
            description = "Tax calculation based on gross monthly salary",
            formula = "netSalary = grossSalary - (grossSalary * totalTaxRate) + deductions",
            note = "Hardcoded data for testing"
        )
    )
}
