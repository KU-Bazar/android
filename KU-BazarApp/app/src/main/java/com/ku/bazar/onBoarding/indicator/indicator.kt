package com.ku.bazar.onBoarding.indicator

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun dot(
    isSelected : Boolean,
    indicatorHeight: Dp,
    selectedIndicatorColor: Color,
    unselectedIndicatorColor: Color
){
    val width = animateDpAsState(
        targetValue = if (isSelected) 26.dp
                        else indicatorHeight,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(modifier = Modifier
        .sizeIn(
            minWidth = indicatorHeight,
            minHeight = indicatorHeight,
            maxHeight = 26.dp,
            maxWidth = 26.dp
        )
        .height(indicatorHeight)
        .width(width.value)
        .clip(CircleShape)
        .background(
            color = if (isSelected) selectedIndicatorColor else unselectedIndicatorColor
        )
    )
}

@Composable
fun floor(
    isSelected: Boolean,
    indicatorHeight: Dp,
    selectedIndicatorColor: Color ,
    unselectedIndicatorColor: Color
){
    Row(verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
        repeat(3){
            dot(
                isSelected = isSelected,
                indicatorHeight = indicatorHeight,
                selectedIndicatorColor = selectedIndicatorColor,
                unselectedIndicatorColor = unselectedIndicatorColor
            )
        }
    }
}