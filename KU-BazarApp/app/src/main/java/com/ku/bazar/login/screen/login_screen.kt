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
import com.ku.bazar.login.component.loginBackground
import com.ku.bazar.login.component.loginButton
import com.ku.bazar.login.component.logo
import com.ku.bazar.login.component.registerButton
import com.ku.bazar.login.util.login

@Composable
@Preview(showSystemUi = true, showBackground = true)

fun loginFirst(){

    loginBackground()

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
