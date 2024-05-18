package com.ku.bazar.onBoarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.util.OnBoarding


@Composable
fun secondScreen(){
    Box(modifier = Modifier.fillMaxSize()){

        Row( modifier = Modifier.fillMaxSize()
        ) {

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(0.dp, 90.dp, 0.dp, 0.dp)
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = OnBoarding.Second.backFirst),
                    contentDescription = "@string/wall_des",
                    Modifier.size(width = 80.dp, height = 140.dp)
                )

                Spacer(modifier = Modifier.height(200.dp))

                Image(
                    painter = painterResource(id = OnBoarding.Second.backThird),
                    contentDescription = "@string/wall_des",
                    Modifier.size(width = 80.dp, height = 80.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .padding(0.dp, 30.dp, 0.dp, 0.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    painter = painterResource(id = OnBoarding.Second.backSecond),
                    contentDescription = "@string/wall_des",
                    Modifier.size(width = 180.dp, height = 180.dp)
                )

                Spacer(modifier = Modifier.height(50.dp))

                Image(
                    painter = painterResource(id = OnBoarding.Second.backFourth),
                    contentDescription = "@string/wall_des",
                    Modifier.size(width = 100.dp, height = 100.dp)
                )
            }
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(30.dp, 90.dp, 0.dp, 0.dp)
                    .size(height = 350.dp, width = 350.dp),
                painter = painterResource(id = OnBoarding.Second.image),
                contentDescription = "@string/wall_des"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = OnBoarding.Second.titleFirst,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = OnBoarding.Second.description_first)

            Spacer(modifier = Modifier.height(3.dp))

            Text(text = OnBoarding.Second.description_second)

            Spacer(modifier = Modifier.height(3.dp))

            Text(text = OnBoarding.Second.description_third)
        }

    }
}
