package com.ku.bazar.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
                text = "Login into KU-Bazar",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "E-mail/Username",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(2.dp))

            TextField(
                value = username,
                onValueChange = {username = it},
                label = {
                    Text(text = "E-mail")
                },
                placeholder = {
                    Text(text = "neern2552@gmail.com")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Password",
                fontWeight = FontWeight.Bold,
                color = Color.Black
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
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier
                    .height(1.dp)
                    .weight(0.6f)
                    .background(color = Color.Gray)
                )

                Text(
                    text = "or",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Box(modifier = Modifier
                    .height(1.dp)
                    .weight(0.6f)
                    .background(color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(3.dp))

                Text(text = loginMain.second.oauth)
            }

            Spacer(modifier = Modifier.height(20.dp))

            loginBigButton(
                modifier = Modifier.fillMaxWidth(), onClick =  {
                    //navHostController.navigate(Screen.Login.route)
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = loginMain.first.account,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(9.dp))

                Text(
                    text = "Register Now",
                    color = Color.Red,
                    modifier = Modifier
                        .clickable {
                            navHostController.navigate(Screen.Register.route)
                        },
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

