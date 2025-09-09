package se.atte.partier.theme

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun ThemePreview(useDarkMode: Boolean = false, content: @Composable () -> Unit) {
    AppTheme(useDarkMode = useDarkMode) {
        Surface {
            content()
        }
    }
}