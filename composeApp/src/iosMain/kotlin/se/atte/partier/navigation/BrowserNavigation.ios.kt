package se.atte.partier.navigation

actual class BrowserNavigationManager {
    actual fun setupBackButtonHandler(onBackPressed: () -> Unit) {
        // No-op for iOS - handled by system back gesture
    }
    
    actual fun pushState(path: String) {
        // No-op for iOS
    }
    
    actual fun replaceState(path: String) {
        // No-op for iOS
    }
    
    actual fun getCurrentPath(): String {
        return "budget"
    }
}
