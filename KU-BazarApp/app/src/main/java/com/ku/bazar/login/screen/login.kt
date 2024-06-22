
package com.ku.bazar.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ku.bazar.R
import com.ku.bazar.login.component.loginBackground
import com.ku.bazar.login.component.loginBigButton
import com.ku.bazar.login.util.loginMain
import com.ku.bazar.navigation.Screen
import com.ku.bazar.ui.theme.SearchBarGray

@Composable
fun login(navHostController: NavHostController){

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    loginBackground()

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(400.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = 60.dp, y = 170.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = loginMain.first.title,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            loginMain.first.username?.let { Text(text = it , fontWeight = FontWeight.Bold) }

            Spacer(modifier = Modifier.height(2.dp))

            TextField(
                value = username,
                onValueChange = {username = it},
                label = {
                    Text(text = "E-mail")
                },
                placeholder = {
                    Text(text = "neerrn2552@gmail.com")
                },
                modifier = Modifier.background(color = SearchBarGray)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = loginMain.first.password,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(2.dp))

            TextField(
                value = password,
                onValueChange = {password = it},
                label = {
                    Text(text = "Password")
                },
                placeholder = {
                    Text(text = "**************")
                },
                modifier = Modifier.background(color = SearchBarGray,),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Box(modifier = Modifier
                    .offset(y = 8.dp)
                    .size(width = 130.dp, height = 2.dp)
                    .background(color = Color.Gray)

                )

                Text(
                    text = "or",
                    fontWeight = FontWeight.Bold
                )

                Box(modifier = Modifier
                    .offset(y = 8.dp)
                    .size(width = 130.dp, height = 2.dp)
                    .background(color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(horizontal = 3.dp))

                Text(text = loginMain.second.oauth)
            }

            Spacer(modifier = Modifier.height(20.dp))

            loginBigButton(
                modifier = Modifier, onClick =  {
//                    navHostController.navigate(Screen.Login.route)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row (
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = loginMain.first.account,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(9.dp))

                Text(
                    text = "Register Now",
                    color = Color.Red,
                    modifier = Modifier
                        .clickable { },
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }

}