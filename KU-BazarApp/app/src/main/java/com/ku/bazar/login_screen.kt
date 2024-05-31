package com.ku.bazar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ku.bazar.util.login

@Composable
@Preview(showSystemUi = true, showBackground = true)

fun login(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(
                    id = login.first.first_background
                ),
                contentDescription = "@string/wall_des"
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topEnd = 25.dp, topStart = 25.dp
                    )
                )
                .align(Alignment.BottomStart)
        ){}
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.cloud_second),
                contentDescription = null,
                modifier = Modifier
                    .size(95.dp, 32.dp)
                    .offset(x = 100.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row() {
                Image(
                    painter = painterResource(id = R.drawable.cloud_first),
                    contentDescription = null,
                    modifier = Modifier
                        .size(57.dp, 18.dp)
                        .offset(x = 50.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.cloud_third),
                    contentDescription = null,
                    modifier = Modifier
                        .size(42.dp, 16.dp)
                        .offset(x = 230.dp)
                )
            }
        }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ){
                Image(painter = painterResource(id = R.drawable.plant),
                    contentDescription = null,
                    modifier = Modifier
                        .size(34.dp,83.dp)
                        .offset(y = (-120).dp)
                )
                Image(painter = painterResource(id = R.drawable.house),
                    contentDescription = null,
                    modifier = Modifier
                        .size(213.dp,179.dp)
                        .offset(y = (-168).dp)
                )
            }
            

    }

}
/*
fun login(){
    Column(modifier = Modifier.fillMaxSize()) {
        val imageHeightFraction = 0.3f
        val offsetHeight = 30.dp

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(imageHeightFraction)
        ){
            Image(
                painter = painterResource(id = R.drawable.login_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(-offsetHeight))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .offset((-70).dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.Black)
        )
    }
}
*/

