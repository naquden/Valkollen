package se.atte.partier.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowRight
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import se.atte.partier.Screen
import se.atte.partier.utils.Quadruple

val standardPaddingLarge = 32.dp
val standardPaddingMedium = 16.dp
val standardPaddingSmall = 8.dp
val minCommonItemHeight = 56.dp

fun Modifier.clickableDefault(enabled: Boolean = true, rippleBound: Boolean = true, onClick: () -> Unit) = composed {
    this.clickable(
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        indication = ripple(bounded = rippleBound),
        onClick = onClick,
    )
}

@Composable
fun CommonItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String? = null,
    subTitle: String? = null,
    subValue: String? = null,
    showDivider: Boolean = true,
    startContent: (@Composable () -> Unit)? = null,
    endContent: (@Composable () -> Unit)? = null
) {
    Column(
        modifier = modifier.padding(top = standardPaddingSmall, start = standardPaddingSmall, end = standardPaddingSmall, bottom = 0.dp)
    ) {
        CommonItemWithoutPadding(
            title = title,
            value = value,
            subTitle = subTitle,
            subValue = subValue,
            showDivider = showDivider,
            startContent = startContent,
            endContent = endContent
        )
    }
}

@Composable
fun CommonItemWithoutPadding(
    modifier: Modifier = Modifier,
    title: String? = null,
    value: String? = null,
    subTitle: String? = null,
    subValue: String? = null,
    showDivider: Boolean = true,
    startContent: (@Composable () -> Unit)? = null,
    endContent: (@Composable () -> Unit)? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (startContent != null) startContent()
            Column(
                modifier = Modifier
                    .defaultMinSize(minHeight = minCommonItemHeight)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    title?.let { Text(text = it, style = MaterialTheme.typography.titleMedium) }
                    value?.let {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = standardPaddingSmall),
                            text = it,
                            textAlign = TextAlign.End,
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    subTitle?.let { Text(text = it, color = MaterialTheme.colorScheme.outline) }
                    subValue?.let {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = standardPaddingSmall),
                            text = it,
                            textAlign = TextAlign.End,
                        )
                    }
                }
            }
            if (endContent != null) endContent()
        }

        if (showDivider) {
            HorizontalDivider(modifier = Modifier.padding(top = standardPaddingSmall))
        } else {
            Spacer(modifier = Modifier.height(standardPaddingSmall))
        }
    }
}


@Composable
fun CommonCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    colors: CardColors = CardDefaults.cardColors(),
    content: @Composable () -> Unit
) {
    val elevation = CardDefaults.cardElevation(2.dp)
    if (onClick != null) {
        Card(
            modifier = modifier,
            elevation = elevation,
            colors = colors,
            onClick = onClick,
        ) {
            content()
        }
    } else {
        Card(
            modifier = modifier,
            elevation = elevation,
            colors = colors,
        ) {
            content()
        }
    }
}

// Chained due to kotlin not supporting default value for vararg variables
@Composable
fun CommonCardButton(
    modifier: Modifier = Modifier,
    title: String,
    icon: (@Composable () -> Unit)? = null,
    showArrow: Boolean = false,
    cardPadding: Dp = standardPaddingMedium,
    enabled: Boolean = true,
    onClickEvent: () -> Unit
) {
    CommonCardButton(
        modifier = modifier,
        title = title,
        subTitle = emptyArray(),
        icon = icon,
        showArrow = showArrow,
        cardPadding = cardPadding,
        enabled = enabled,
        onClickEvent = onClickEvent
    )
}

@Composable
fun CommonCardButton(
    modifier: Modifier = Modifier,
    title: String,
    vararg subTitle: String,
    icon: (@Composable () -> Unit)? = null,
    showArrow: Boolean = false,
    cardPadding: Dp = standardPaddingMedium,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.medium,
    border: BorderStroke? = null,
    cardColor: CardColors = CardDefaults.cardColors(),
    onClickEvent: () -> Unit
) {
    Card(
        modifier = modifier.testTag("test_$title"),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = shape,
        onClick = onClickEvent,
        enabled = enabled,
        border = border,
        colors = cardColor
    ) {
        Row(
            modifier = Modifier.padding(cardPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                it.invoke()
                Spacer(modifier = Modifier.width(standardPaddingMedium))
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                subTitle.forEach {
                    Text(text = it, style = MaterialTheme.typography.bodySmall)
                }
            }
            if (showArrow) {
                Icon(
                    modifier = Modifier
                        .fillMaxHeight(),
                    imageVector = Icons.AutoMirrored.Rounded.ArrowRight,
                    contentDescription = null,
                )
            }
        }
    }
}

private fun getNavigationItems(navController: NavController) = listOf(
    Quadruple(
        Screen.Budget,
        Icons.Rounded.Wallet,
        "Budget",
        { navController.navigate(Screen.Budget) },
    ),
)

@Composable
fun BottomNavBar(modifier: Modifier = Modifier, navController: NavController) {
    NavigationBar(
        modifier = modifier
    ) {
        getNavigationItems(navController).forEach { pairs ->
            val (screen, icon, title, onClick) = pairs
            NavigationBarItem(
                icon = {
                    Icon(imageVector = icon, contentDescription = title)
                },
                label = { Text(title, textAlign = TextAlign.Center) },
                selected = true,// TODO: proper selection when more than one item
                onClick = onClick,
            )
        }
    }
}

@Composable
fun NavRail(navController: NavController) {
    NavigationRail(
        modifier = Modifier.fillMaxHeight(), // Make the rail take the full height
        header = {
            // Optional: Add a header, like a logo or a floating action button
            // Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
        }
    ) {
        getNavigationItems(navController).forEach { pairs ->
            val (screen, icon, title, onClick) = pairs
            NavigationRailItem(
                icon = {
                    Icon(imageVector = icon, contentDescription = title)
                },
                label = { Text(title, textAlign = TextAlign.Center) },
                selected = true,// TODO: proper selection when more than one item
                onClick = onClick,
            )
        }
    }
}