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
        color = Color(0xFF4AE53F) // Miljöpartiet Green
    ),
    S(
        code = "S",
        displayName = "Socialdemokraterna (S)",
        color = Color(0xFFEF4444) // Red
    ),
    C(
        code = "C",
        displayName = "Centerpartiet (C)",
        color = Color(0xFF255821) // Centerpartiet Green
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
        val DEFAULT_COLOR = Color(0x000000) // Black

        fun fromCode(code: String): Party? {
            return entries.find { it.code.equals(code, ignoreCase = true) }
        }

        fun getColorByCode(partyCode: String): Color {
            return Party.fromCode(partyCode)?.color ?: Party.DEFAULT_COLOR
        }
    }
}
