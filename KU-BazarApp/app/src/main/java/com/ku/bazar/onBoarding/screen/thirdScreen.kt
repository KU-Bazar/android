package com.ku.bazar.onBoarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.util.OnBoarding

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun thirdScreen(){
    Box(modifier = Modifier.fillMaxSize()){

        Row(modifier = Modifier.fillMaxSize()) {

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(0.dp, 100.dp, 0.dp, 0.dp)
                    .weight(2f)
            ) {
                Image(
                    painter = painterResource(id = OnBoarding.Third.backFirst),
                    contentDescription = "@string/wall_des",
                    Modifier.size(width = 50.dp, height = 190.dp)
                )

                Spacer(modifier = Modifier.height(120.dp))

                Image(
                    painter = painterResource(id = OnBoarding.Third.backSecond),
                    contentDescription = "@string/wall_des",
                    Modifier.size(width = 80.dp, height = 80.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .padding(0.dp, 130.dp, 0.dp, 0.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    painter = painterResource(id = OnBoarding.Third.backThird),
                    contentDescription = "@string/wall_des",
                    Modifier.size(width = 80.dp, height = 80.dp)
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(30.dp, 90.dp, 0.dp, 0.dp)
                    .size(height = 350.dp, width = 350.dp),
                painter = painterResource(id = OnBoarding.Third.image),
                contentDescription = "@string/wall_des"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = OnBoarding.Third.titleFirst,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = OnBoarding.Third.description_first)

            Spacer(modifier = Modifier.height(3.dp))

            Text(text = OnBoarding.Third.description_second)

        }
    }
}