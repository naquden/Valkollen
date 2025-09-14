package se.atte.partier.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import partier.composeapp.generated.resources.Res
import partier.composeapp.generated.resources.ic_arrow_back
import partier.composeapp.generated.resources.ic_arrow_right
import partier.composeapp.generated.resources.ic_expense
import partier.composeapp.generated.resources.ic_income
import partier.composeapp.generated.resources.ic_party_details

/**
 * Helper object for accessing Heroicons as drawable resources
 */
object HeroIcons {

    /**
     * Expense icon - represents a receipt or expense document
     */
    val Expense = Res.drawable.ic_expense
    
    /**
     * Income icon - represents money/income with dollar sign
     */
    val Income = Res.drawable.ic_income
    
    /**
     * Party details icon - represents comparison/arrows for party details
     */
    val PartyDetails = Res.drawable.ic_party_details
    
    /**
     * Arrow right icon - represents navigation/forward direction
     */
    val ArrowRight = Res.drawable.ic_arrow_right
    
    /**
     * Arrow back icon - represents navigation/back direction
     */
    val ArrowBack = Res.drawable.ic_arrow_back
}

/**
 * Extension function to easily get a painter for a Heroicon
 */
@Composable
fun DrawableResource.painter() = painterResource(this)
