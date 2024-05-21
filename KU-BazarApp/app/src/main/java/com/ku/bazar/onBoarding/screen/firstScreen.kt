package com.ku.bazar.onBoarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.util.OnBoarding

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun firstScreen(){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxSize()) {

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(0.dp, 90.dp, 0.dp, 0.dp)
                    .weight(1f)
            ) {
                Image(
                    modifier = Modifier
                        .size(height = 230.dp, width = 190.dp),
                    painter = painterResource(id = OnBoarding.First.backFourth),
                    contentDescription =" @string/wall_des"
                )

                Spacer(modifier = Modifier.height(150.dp))

                Image(
                    modifier = Modifier
                        .size(height = 100.dp, width = 100.dp),
                    painter = painterResource(id = OnBoarding.First.backFirst),
                    contentDescription = "@string/wall_des"
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
                    modifier = Modifier
                        .size(height = 130.dp, width = 80.dp),
                    painter = painterResource(id = OnBoarding.First.backSecond),
                    contentDescription =" @string/wall_des"
                )

                Spacer(modifier = Modifier.height(140.dp))

                Image(
                    modifier = Modifier
                        .size(height = 100.dp, width = 100.dp),
                    painter = painterResource(id = OnBoarding.First.backThird),
                    contentDescription =" @string/wall_des"
                )
            }
        }
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(30.dp, 120.dp, 0.dp, 0.dp)
                    .size(height = 350.dp, width = 350.dp),
                painter = painterResource(id = OnBoarding.First.image),
                contentDescription = "@string/con_description"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = OnBoarding.First.titleFirst,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = OnBoarding.First.description_first,
                fontSize = 15.sp
            )

            Text(
                text = OnBoarding.First.description_second,
                fontSize = 15.sp
            )

        }
    }
}