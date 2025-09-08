package se.atte.partier.navigation

actual class BrowserNavigationManager {
    actual fun setupBackButtonHandler(onBackPressed: () -> Unit) {
        // No-op for Android - handled by system back button
    }
    
    actual fun pushState(path: String) {
        // No-op for Android
    }
    
    actual fun replaceState(path: String) {
        // No-op for Android
    }
    
    actual fun getCurrentPath(): String {
        return "budget"
    }
}
