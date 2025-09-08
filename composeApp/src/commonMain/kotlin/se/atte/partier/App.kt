package se.atte.partier

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.atte.partier.navigation.AppNavigation
import se.atte.partier.theme.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        AppNavigation()
    }
}