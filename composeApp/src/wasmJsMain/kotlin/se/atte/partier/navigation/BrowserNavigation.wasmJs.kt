package se.atte.partier.navigation

import org.w3c.dom.events.Event
import kotlinx.browser.window

actual class BrowserNavigationManager {
    private var onBackPressed: (() -> Unit)? = null
    private var isListenerAdded = false
    
    actual fun setupBackButtonHandler(onBackPressed: () -> Unit) {
        this.onBackPressed = onBackPressed
        if (!isListenerAdded) {
            window.addEventListener("popstate") { _: Event ->
                try {
                    onBackPressed()
                } catch (e: Throwable) {
                    // Silently fail for now
                }
            }
            isListenerAdded = true
        }
    }
    
    actual fun pushState(path: String) {
        try {
            window.history.pushState(null, "", "/$path")
        } catch (e: Throwable) {
            // Silently fail for now
        }
    }
    
    actual fun replaceState(path: String) {
        try {
            window.history.replaceState(null, "", "/$path")
        } catch (e: Throwable) {
            // Silently fail for now
        }
    }
    
    actual fun getCurrentPath(): String {
        return try {
            window.location.pathname.removePrefix("/")
        } catch (e: Throwable) {
            "budget"
        }
    }
}
