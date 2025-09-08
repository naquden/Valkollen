package se.atte.partier.constants

import androidx.compose.ui.graphics.Color

enum class Party(
    val code: String,
    val displayName: String,
    val color: Color
) {
    SD(
        code = "SD",
        displayName = "Sverigedemokraterna (SD)",
        color = Color(0xFF1B365D) // Dark Blue
    ),
    M(
        code = "M",
        displayName = "Moderaterna (M)",
        color = Color(0xFF0066CC) // Blue
    ),
    MP(
        code = "MP",
        displayName = "Miljöpartiet (MP)",
        color = Color(0xFF059669) // Dark Green
    ),
    S(
        code = "S",
        displayName = "Socialdemokraterna (S)",
        color = Color(0xFFEF4444) // Red
    ),
    C(
        code = "C",
        displayName = "Centerpartiet (C)",
        color = Color(0xFF10B981) // Green
    ),
    L(
        code = "L",
        displayName = "Liberalerna (L)",
        color = Color(0xFF60A5FA) // Light Blue
    ),
    KD(
        code = "KD",
        displayName = "Kristdemokraterna (KD)",
        color = Color(0xFF3B82F6) // Blue
    ),
    V(
        code = "V",
        displayName = "Vänsterpartiet (V)",
        color = Color(0xFFDC2626) // Red
    );
    
    companion object {
        // Fallback color for unknown parties
        val DEFAULT_COLOR = Color(0xFF6750A4) // Purple
        
        /**
         * Get party by code
         */
        fun fromCode(code: String): Party? {
            return values().find { it.code.equals(code, ignoreCase = true) }
        }
        
        /**
         * Get party by display name (supports partial matching)
         */
        fun fromDisplayName(displayName: String): Party? {
            return values().find { 
                displayName.contains(it.displayName.split(" ")[0], ignoreCase = true) ||
                displayName.contains(it.code, ignoreCase = true)
            }
        }
        
        /**
         * Get all available parties
         */
        fun getAllParties(): List<Party> {
            return values().toList()
        }
        
        /**
         * Get all party codes
         */
        fun getAllCodes(): List<String> {
            return values().map { it.code }
        }
        
        /**
         * Get all display names
         */
        fun getAllDisplayNames(): List<String> {
            return values().map { it.displayName }
        }
        
        /**
         * Check if a code is valid
         */
        fun isValidCode(code: String): Boolean {
            return fromCode(code) != null
        }
    }
}
