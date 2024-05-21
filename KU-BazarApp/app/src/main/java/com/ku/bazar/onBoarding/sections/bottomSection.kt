package com.ku.bazar.onBoarding.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.ku.bazar.onBoarding.indicator.floor
import com.ku.bazar.R

@Composable
fun bottomSection(
                onArrowClick: () -> Unit ,
                skipClicked: () -> Unit,
                currentPage: Int,
                indicatorHeight: Dp,
                selectedIndicatorColor: Color,
                unselectedIndicatorColor: Color
){
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        TextButton(onClick = skipClicked) {
            Text(text = "Skip")
        }

        floor(
            currentPage = currentPage,
            indicatorHeight = indicatorHeight,
            selectedIndicatorColor = selectedIndicatorColor,
            unselectedIndicatorColor = unselectedIndicatorColor
        )

        FloatingActionButton(
            onClick = onArrowClick
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null
            )
        }
    }
}