package se.atte.partier.theme

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun ThemePreview(content: @Composable () -> Unit) {
    AppTheme() {
        Surface {
            content()
        }
    }
}