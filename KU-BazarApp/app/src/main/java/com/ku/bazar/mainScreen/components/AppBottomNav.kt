package com.ku.bazar.mainScreen.components

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier.*
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import com.ku.bazar.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import com.ku.bazar.mainScreen.data.Screen
import kotlin.math.roundToInt
import com.ku.bazar.ui.theme.Dimension


@Composable
fun AppBottomNav(
    modifier: Modifier = Modifier,
    activeRoute: String,
    bottomNavDestinations: List<Screen>,
    backgroundColor: Color,
    onCartOffsetMeasured: (offset: IntOffset) -> Unit,
    onActiveRouteChange: (route: String) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        var cartOffsetY by remember { mutableStateOf(0) }
        val density = LocalDensity.current


        Row(
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    cartOffsetY = coordinates.size.height
                }
                .fillMaxWidth()
                .padding(horizontal = Dimension.pagePadding, vertical = Dimension.pagePadding / 2),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            bottomNavDestinations.forEachIndexed { index, screen ->
                val isActive = activeRoute.equals(screen.route, ignoreCase = true)
                AppBottomNavItem(
                    active = isActive,
                    title = stringResource(id = screen.title ?: R.string.home),
                    icon = screen.icon ?: R.drawable.ic_home_empty,
                    onRouteClicked = {
                        if (!isActive) {
                            onActiveRouteChange(screen.route)
                        }
                    }
                )
                if (index == bottomNavDestinations.size / 2 - 1) {
                    Spacer(modifier = Modifier.width(Dimension.smIcon))
                }
            }
        }

        DrawableButton(
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    val offset = coordinates.positionInWindow()
                    onCartOffsetMeasured(
                        IntOffset(
                            x = offset.x.roundToInt() - Dimension.xlIcon.value.toInt(),
                            y = offset.y.roundToInt() + cartOffsetY / 2
                        )
                    )
                }
                .align(Alignment.TopCenter)
                .offset{
                    IntOffset(
                        y = with(density) { (-cartOffsetY / 2).toDp().roundToPx() },
                        x = 0,
                    )
                }
                .border(width = Dimension.sm / 2, color = MaterialTheme.colors.primary, shape = CircleShape),
            painter = painterResource(id = R.drawable.ic_shopping_bag),
            backgroundColor = if (activeRoute == Screen.Cart.route) MaterialTheme.colors.primary else MaterialTheme.colors.background,
            iconSize = Dimension.mdIcon * 0.8f,
            iconTint = if (activeRoute == Screen.Cart.route) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
            onButtonClicked = { onActiveRouteChange(Screen.Cart.route) },
            shape = CircleShape,
            paddingValue = PaddingValues(Dimension.md)
        )
    }
}

@Composable
fun AppBottomNavItem(
    modifier: Modifier = Modifier,
    active: Boolean,
    title: String,
    icon: Int,
    onRouteClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .clickable { onRouteClicked() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(
            modifier = Modifier
                .padding(bottom = Dimension.sm)
                .fillMaxWidth()
                .height(Dimension.sm)
                .clip(MaterialTheme.shapes.medium)
                .background(if (active) MaterialTheme.colors.primary else Color.Transparent)
        )
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = if (active) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
            modifier = Modifier.size(Dimension.smIcon)
        )
    }
}