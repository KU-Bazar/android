package com.ku.bazar.onBoarding.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ku.bazar.onBoarding.indicator.floor
import com.ku.bazar.R
import com.ku.bazar.ui.theme.PrimaryPink

@Composable
fun bottomSection(
                onArrowClick: () -> Unit ,
                skipClicked: () -> Unit,
                currentPage: Int,
                indicatorHeight: Dp,
                selectedIndicatorColor: Color,
                unselectedIndicatorColor: Color
){
    Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(10.dp)) {
        TextButton(onClick = skipClicked) {
            Text(
                text = "Skip",
                color = PrimaryPink
            )
        }

        floor(
            currentPage = currentPage,
            indicatorHeight = indicatorHeight,
            selectedIndicatorColor = selectedIndicatorColor,
            unselectedIndicatorColor = unselectedIndicatorColor
        )
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))

        FloatingActionButton(
            onClick = onArrowClick
        ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null,
                    Modifier.size(20.dp),
                    contentScale = ContentScale.Crop
                )
        }
    }
}