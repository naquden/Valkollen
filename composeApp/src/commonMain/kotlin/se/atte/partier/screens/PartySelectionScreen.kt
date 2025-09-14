package se.atte.partier.screens

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import partier.composeapp.generated.resources.Res
import partier.composeapp.generated.resources.ic_arrow_right
import partier.composeapp.generated.resources.nav_back
import se.atte.partier.components.CommonCard
import se.atte.partier.components.HeroIcons
import se.atte.partier.components.painter
import se.atte.partier.components.standardPaddingMedium
import se.atte.partier.components.standardPaddingSmall
import se.atte.partier.constants.Party
import se.atte.partier.theme.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartySelectionScreen(
    onBackClick: () -> Unit,
    onPartySelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "Specifik parti",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = standardPaddingMedium)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(standardPaddingMedium)
            ) {
                Text(
                    text = "Välj parti",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(standardPaddingSmall))

                Text(
                    text = "Se varje partis detaljerade budgetförslag med utgifter och inkomster",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Party List
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(Party.entries.toTypedArray()) { party ->
                    PartyCard(
                        partyName = party.displayName,
                        partyCode = party.code,
                        onClick = { onPartySelected(party.code) }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(standardPaddingMedium))
                }
            }
        }
    }
}

@Composable
private fun PartyCard(
    partyName: String,
    partyCode: String,
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
            // Party icon/color indicator
            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(8.dp),
                color = Party.getColorByCode(partyCode),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = partyCode,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
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

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Klicka för att se detaljerad budget",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Arrow indicator
            Icon(painter = painterResource(Res.drawable.ic_arrow_right), contentDescription = null)
        }
    }
}

@Preview
@Composable
fun PreviewPartySelectionScreen() {
    ThemePreview {
        PartySelectionScreen(
            onBackClick = {},
            onPartySelected = {}
        )
    }
}

@Preview
@Composable
fun PreviewPartySelectionScreen_Dark() {
    ThemePreview(useDarkMode = true) {
        PartySelectionScreen(
            onBackClick = {},
            onPartySelected = {}
        )
    }
}