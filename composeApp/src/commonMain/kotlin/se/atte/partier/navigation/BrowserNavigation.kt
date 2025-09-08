package se.atte.partier.navigation

expect class BrowserNavigationManager() {
    fun setupBackButtonHandler(onBackPressed: () -> Unit)
    fun pushState(path: String)
    fun replaceState(path: String)
    fun getCurrentPath(): String
}
