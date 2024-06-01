package com.ku.bazar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.login.component.loginButton
import com.ku.bazar.login.component.logo
import com.ku.bazar.login.component.registerButton
import com.ku.bazar.login.util.login

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
                    id = login.main.first_background
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

            Spacer(modifier = Modifier.height(90.dp))

            Row {
                Image(
                    painter = painterResource(id = R.drawable.cloud_first),
                    contentDescription = null,
                    modifier = Modifier
                        .size(57.dp, 18.dp)
                        .offset(x = 40.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.cloud_third),
                    contentDescription = null,
                    modifier = Modifier
                        .size(42.dp, 16.dp)
                        .offset(x = 270.dp)
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
                        .size(34.dp, 83.dp)
                        .offset(y = (-120).dp)
                )
                Image(painter = painterResource(id = R.drawable.house),
                    contentDescription = null,
                    modifier = Modifier
                        .size(213.dp, 179.dp)
                        .offset(y = (-168).dp)
                )
            }
            

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(x = 60.dp, y = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
            logo(
                modifier = Modifier
                    .size(80.dp)
            )
        
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = login.main.title,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = login.main.description,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        loginButton(
            modifier = Modifier.clickable {  }
        )

        registerButton(
            modifier = Modifier.clickable {  }
        )

    }


}
