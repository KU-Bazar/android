package com.ku.bazar.login.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ku.bazar.login.component.loginBackground
import com.ku.bazar.login.component.loginButton
import com.ku.bazar.login.component.logo
import com.ku.bazar.login.component.registerButton
import com.ku.bazar.login.util.login
import com.ku.bazar.navigation.Screen

@Composable
fun loginFirst(navHostController: NavHostController){

    loginBackground()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(x = 60.dp, y = 120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        logo(
            modifier = Modifier
                .size(80.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = login.main.title,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = login.main.description,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(50.dp))

        Row() {
            loginButton(
                modifier = Modifier, onClick =  {
                    navHostController.navigate(Screen.Login.route)
                }
            )

            Spacer(modifier = Modifier.padding(horizontal = 20.dp))

            registerButton(
                modifier = Modifier, onClick = {
                    navHostController.navigate(Screen.Register.route)
                }
            )
        }

    }
}