package se.atte.partier.constants

import androidx.compose.ui.graphics.Color

object PartyColors {
    /**
     * Get party color by party code
     */
    fun getColorByCode(partyCode: String): Color {
        return Party.fromCode(partyCode)?.color ?: Party.DEFAULT_COLOR
    }
    
    /**
     * Get party color by party name (supports partial matching)
     */
    fun getColorByName(partyName: String): Color {
        return Party.fromDisplayName(partyName)?.color ?: Party.DEFAULT_COLOR
    }
}
