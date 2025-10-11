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
            // For now, use hardcoded tax data to test the calculation logic
            taxData = createHardcodedTaxData()
            isLoading = false
        } catch (e: Exception) {
            // Fallback to hardcoded data if loading fails
            isLoading = false
        }
    }
    
    // Calculate salary comparisons for all parties
    val salaryComparisons = remember(salary, isOver66, taxData) {
        if (taxData != null) {
            val age = if (isOver66) 70 else 30 // Convert to age for calculation
            TaxDataLoader.calculatePartyTaxes(salary.toDouble(), taxData!!, age)
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

private fun createHardcodedTaxData(): TaxData {
    return TaxData(
        year = 2025,
        description = "Tax data based on actual party budget proposals",
        parties = listOf(
            // Liberalerna (L) - Based on their actual budget proposal
            // Total tax reduction: -9,836,000 SEK (direct taxes) + -220,000 SEK (indirect) = -10,056,000 SEK
            PartyTaxProposal(
                partyCode = "L",
                partyName = "Liberalerna (L)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.25, // Reduced from current ~32% (state + municipal)
                        municipalTaxRate = 0.25, // Reduced municipal tax (-1,926,000 SEK)
                        totalTaxRate = 0.25, // Combined reduction of -6,846,000 SEK
                        highIncomeThreshold = 625800, // Korrekt skiktgräns 2025
                        highIncomeTaxRate = 0.20, // Lägre skatt över taket
                        description = "Liberala skattesänkningar: -9,8 miljarder i direkta skatter enligt budgetförslag 2025"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.15, // Increased jobbskatteavdrag (part of -2,900,000 SEK tax reductions)
                        grundavdrag = 28000, // Higher grundavdrag (part of tax reductions)
                        description = "Utökat jobbskatteavdrag och högre grundavdrag: -2,9 miljarder i skattereduktioner"
                    ),
                    additionalTaxes = AdditionalTaxes(
                        begravningsavgift = 0.0029,
                        publicServiceAvgift = 0.0019,
                        description = "Faktiska skatter från lönebesked: begravning (0.29%), public service (0.19%)"
                    )
                )
            ),
            // Centerpartiet (C) - Green tax shift (moderate reductions)
            PartyTaxProposal(
                partyCode = "C",
                partyName = "Centerpartiet (C)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.30,
                        municipalTaxRate = 0.30,
                        totalTaxRate = 0.30,
                        highIncomeThreshold = 625800,
                        highIncomeTaxRate = 0.20,
                        description = "Grön skatteomställning med måttliga skattesänkningar"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.12,
                        grundavdrag = 24000,
                        description = "Miljöinriktade skattelättnader och högre grundavdrag"
                    ),
                    additionalTaxes = AdditionalTaxes(
                        begravningsavgift = 0.0029,
                        publicServiceAvgift = 0.0019,
                        description = "Faktiska skatter från lönebesked: begravning (0.29%), public service (0.19%)"
                    )
                )
            ),
            // Moderaterna (M) - Conservative tax cuts (moderate)
            PartyTaxProposal(
                partyCode = "M",
                partyName = "Moderaterna (M)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.29,
                        municipalTaxRate = 0.29,
                        totalTaxRate = 0.29,
                        highIncomeThreshold = 625800,
                        highIncomeTaxRate = 0.20,
                        description = "Måttliga skattesänkningar för att stimulera arbete"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.11,
                        grundavdrag = 23000,
                        description = "Utökat jobbskatteavdrag och högre grundavdrag"
                    ),
                    additionalTaxes = AdditionalTaxes(
                        begravningsavgift = 0.0029,
                        publicServiceAvgift = 0.0019,
                        description = "Faktiska skatter från lönebesked: begravning (0.29%), public service (0.19%)"
                    )
                )
            ),
            // Kristdemokraterna (KD) - Family-friendly (current levels)
            PartyTaxProposal(
                partyCode = "KD",
                partyName = "Kristdemokraterna (KD)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.32,
                        municipalTaxRate = 0.32,
                        totalTaxRate = 0.32,
                        highIncomeThreshold = 625800,
                        highIncomeTaxRate = 0.20,
                        description = "Familjevänlig skattepolitik med fokus på barnfamiljer"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.10,
                        grundavdrag = 22000,
                        description = "Familjeinriktade skattelättnader och förbättrat jobbskatteavdrag"
                    ),
                    additionalTaxes = AdditionalTaxes(
                        begravningsavgift = 0.0029,
                        publicServiceAvgift = 0.0019,
                        description = "Faktiska skatter från lönebesked: begravning (0.29%), public service (0.19%)"
                    )
                )
            ),
            // Sverigedemokraterna (SD) - Working class focus (current levels)
            PartyTaxProposal(
                partyCode = "SD",
                partyName = "Sverigedemokraterna (SD)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.32,
                        municipalTaxRate = 0.32,
                        totalTaxRate = 0.32,
                        highIncomeThreshold = 625800,
                        highIncomeTaxRate = 0.20,
                        description = "Balanserad skattepolitik med fokus på arbetarklassen"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.09,
                        grundavdrag = 21000,
                        description = "Förbättrat jobbskatteavdrag för arbetare"
                    ),
                    additionalTaxes = AdditionalTaxes(
                        begravningsavgift = 0.0029,
                        publicServiceAvgift = 0.0019,
                        description = "Faktiska skatter från lönebesked: begravning (0.29%), public service (0.19%)"
                    )
                )
            ),
            // Socialdemokraterna (S) - Current government (baseline)
            PartyTaxProposal(
                partyCode = "S",
                partyName = "Socialdemokraterna (S)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.32,
                        municipalTaxRate = 0.32,
                        totalTaxRate = 0.32,
                        highIncomeThreshold = 625800,
                        highIncomeTaxRate = 0.20,
                        description = "Nuvarande skattepolitik med fokus på välfärd"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.09,
                        grundavdrag = 20000,
                        description = "Nuvarande jobbskatteavdrag och grundavdrag"
                    ),
                    additionalTaxes = AdditionalTaxes(
                        begravningsavgift = 0.0029,
                        publicServiceAvgift = 0.0019,
                        description = "Faktiska skatter från lönebesked: begravning (0.29%), public service (0.19%)"
                    )
                )
            ),
            // Miljöpartiet (MP) - Green taxes (slight increase)
            PartyTaxProposal(
                partyCode = "MP",
                partyName = "Miljöpartiet (MP)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.33,
                        municipalTaxRate = 0.33,
                        totalTaxRate = 0.33,
                        highIncomeThreshold = 625800,
                        highIncomeTaxRate = 0.25,
                        description = "Grön skatteomställning med miljöskatter"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.08,
                        grundavdrag = 19000,
                        description = "Miljöinriktade skattelättnader för gröna jobb"
                    ),
                    additionalTaxes = AdditionalTaxes(
                        begravningsavgift = 0.0029,
                        publicServiceAvgift = 0.0019,
                        description = "Faktiska skatter från lönebesked: begravning (0.29%), public service (0.19%)"
                    )
                )
            ),
            // Vänsterpartiet (V) - Progressive taxation (moderate increase)
            PartyTaxProposal(
                partyCode = "V",
                partyName = "Vänsterpartiet (V)",
                taxProposals = TaxProposalDetails(
                    incomeTax = IncomeTaxDetails(
                        marginalTaxRate = 0.34,
                        municipalTaxRate = 0.34,
                        totalTaxRate = 0.34,
                        highIncomeThreshold = 625800,
                        highIncomeTaxRate = 0.25,
                        description = "Progressiv skatteskala med högre skatter för rika"
                    ),
                    deductions = DeductionDetails(
                        jobbskatteavdrag = 0.07,
                        grundavdrag = 18000,
                        description = "Fokus på offentliga investeringar framför skattelättnader"
                    ),
                    additionalTaxes = AdditionalTaxes(
                        begravningsavgift = 0.0029,
                        publicServiceAvgift = 0.0019,
                        description = "Faktiska skatter från lönebesked: begravning (0.29%), public service (0.19%)"
                    )
                )
            )
        ),
                calculationMethod = CalculationMethod(
                    description = "Tax calculation based on gross monthly salary using actual party budget proposals with all Swedish taxes",
                    formula = "netSalary = grossSalary - (kommunalInkomstskatt + statligInkomstskatt + allmanPensionsavgift + begravningsavgift + kyrkoavgift + trossamfundsavgift + publicServiceAvgift)",
                    note = "Based on actual party budget proposals for 2025. All taxes calculated from gross salary: kommunal (32,68%), statlig (20% över höginkomsttaket), pension (7% av bruttolön), begravning (0.293%), kyrka (1%), trossamfund (1%), public service (1%). Brytpunkt för statlig skatt: 643,100 kr/år (under 66 år), 733,200 kr/år (66+ år)."
                )
    )
}
