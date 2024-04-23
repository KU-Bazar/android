package com.ku.bazar

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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun firstScreen(){
    Box(modifier = Modifier.fillMaxSize()) {
        
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment =  Alignment.Start) {
            Image(
                modifier = Modifier
                    .padding(0.dp, 90.dp, 0.dp, 0.dp)
                    .size(height = 180.dp, width = 180.dp),
                painter = painterResource(id = OnBoarding.First.backFourth),
                contentDescription =" @string/wall_des"
            )

            Image(
                modifier = Modifier
                    .padding(0.dp, 170.dp, 0.dp, 0.dp)
                    .size(height = 100.dp, width = 100.dp),
                painter = painterResource(id = OnBoarding.First.backFirst),
                contentDescription = "@string/wall_des"
            )
            
        }

        Column(modifier = Modifier.fillMaxSize() , horizontalAlignment = Alignment.End) {

            Image(
                modifier = Modifier
                    .padding(0.dp, 110.dp, 0.dp, 0.dp)
                    .size(height = 100.dp, width = 100.dp),
                painter = painterResource(id = OnBoarding.First.backSecond),
                contentDescription =" @string/wall_des"
            )

            Image(
                modifier = Modifier
                    .padding(0.dp, 140.dp, 0.dp, 0.dp)
                    .size(height = 100.dp, width = 100.dp),
                painter = painterResource(id = OnBoarding.First.backThird),
                contentDescription =" @string/wall_des"
            )


        }
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
    )
    {
        Image(
            modifier = Modifier
                .padding(0.dp, 60.dp, 0.dp, 0.dp)
                .align(Alignment.CenterHorizontally)
                .size(height = 350.dp, width = 350.dp),
            painter = painterResource(id = OnBoarding.First.image),
            contentDescription = "@string/con_description"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = OnBoarding.First.titleFirst,
            fontSize = 25.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = OnBoarding.First.description_first,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = OnBoarding.First.description_second,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }

}