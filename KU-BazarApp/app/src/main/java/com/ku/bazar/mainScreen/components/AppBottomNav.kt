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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ku.bazar.mainScreen.data.MainScreen
import com.ku.bazar.navigation.Screen
import kotlin.math.roundToInt
import com.ku.bazar.ui.theme.Dimension
import com.ku.bazar.ui.theme.PrimaryPink
import com.ku.bazar.ui.theme.TextBlack
import com.ku.bazar.addProduct.addProduct


@Composable
fun AddProductButton(
    modifier: Modifier = Modifier,
    onAddProduct: () -> Unit
) {
    DrawableButton(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_shopping_bag),
        backgroundColor = Color.White,
        iconSize = Dimension.mdIcon * 0.8f,
        iconTint = Color.Gray.copy(alpha = 0.5f),
        onButtonClicked = {
            onAddProduct() // Call the callback function
        },
        shape = CircleShape,
        paddingValue = PaddingValues(Dimension.md)
    )
}




@Composable
fun AppBottomNav(
    modifier: Modifier = Modifier,
    activeRoute: String,
    bottomNavDestinations: List<MainScreen>,
    backgroundColor: Color= Color.Black,
    onCartOffsetMeasured: (offset: IntOffset) -> Unit,
    onActiveRouteChange: (route: String) -> Unit,
    navHostController: NavHostController
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
                .padding(horizontal = Dimension.pagePadding, vertical = Dimension.pagePadding / 4),
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
                        y = with(density) { (-cartOffsetY / 3).toDp().roundToPx() },
                        x = 0,
                    )
                }
                .border(width = 2.dp, color = PrimaryPink, shape = CircleShape),
            painter = painterResource(id = R.drawable.ic_shopping_bag),
            backgroundColor = if (activeRoute == MainScreen.Cart.route) PrimaryPink else Color.White,
            iconSize = 18.dp,
            iconTint = if (activeRoute == MainScreen.Cart.route) Color(0xFFF8F8FF) else TextBlack.copy(alpha = 1f),
            onButtonClicked = {
                onActiveRouteChange(MainScreen.Cart.route)
                navHostController.navigate(Screen.Sell.route)

            },

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
    Box(
        modifier = modifier
            .clickable { onRouteClicked() }
            .padding(10.dp)
            .width(IntrinsicSize.Min),
        contentAlignment = Alignment.Center
    ) {

        Spacer(
            modifier = Modifier
                .padding(bottom = Dimension.sm)
                .fillMaxWidth()
                .height(Dimension.sm)
                .clip(MaterialTheme.shapes.medium)
                .background(if (active) PrimaryPink else Color.Transparent)
        )
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = if (active) PrimaryPink else Color.Black.copy(alpha = 0.5f),
            modifier = Modifier.size(Dimension.smIcon)
        )
    }
}
