package com.ku.bazar

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.ku.bazar.util.login

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun login(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(modifier = Modifier.fillMaxSize()
        ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.35f.dp),
                    painter = painterResource(
                        id = login.first.first_background
                    ),
                    contentDescription = "@string/wall_des"
                )
            }
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.65f.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = (-100).dp)
                ){
                    drawRoundRect(
                        color = Color.White,
                        cornerRadius = CornerRadius(100f,100f)
                    )
                }
            }

        }