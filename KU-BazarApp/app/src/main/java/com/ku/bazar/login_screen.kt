package com.ku.bazar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ku.bazar.util.login

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun login(){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Image(
                    modifier =Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    painter = painterResource(
                        id = login.first.first_background
                    ),
                    contentDescription = "@string/wall_des"
                )
            }

            Box(
                modifier = Modifier
                    .weight(2f)
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .aspectRatio(1f)

                ) {
                    drawRoundRect(
                        color = Color.White,
                        size = Size(size.maxDimension, size.maxDimension),
                        cornerRadius = CornerRadius(10f, 10f)
                    )
                }
            }
        }
    }
}