package com.ku.bazar.login.screen

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.login.component.loginBackground
import com.ku.bazar.login.component.loginBigButton
import com.ku.bazar.login.util.loginMain

@Composable
fun login(){

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    loginBackground()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(x = 60.dp, y = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = loginMain.first.title,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        loginMain.first.username?.let { Text(text = it , fontWeight = FontWeight.Bold) }

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = username,
            onValueChange = {username = it},
            label = {
                Text(text = "neerrn2552@gmail.com")
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = loginMain.first.password,
            fontWeight = FontWeight.Bold 
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = password,
            onValueChange = {password = it},
            label = {
                Text(text = "********")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password )
        )

        Spacer(modifier = Modifier.height(30.dp))
        
        Row {
            Box(modifier = Modifier
                .size(width = 50.dp, height = 5.dp)
                .background(color = Color.Gray)
            )

            Text(
                text = "or",
                fontWeight = FontWeight.Bold
            )

            Box(modifier = Modifier
                .size(width = 50.dp, height = 5.dp)
                .background(color = Color.Gray)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        loginBigButton(modifier = Modifier.clickable {  })

        Spacer(modifier = Modifier.height(30.dp))

        Row {
            Text(
                text = loginMain.first.account,
            fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(9.dp))

            Text(
                text = "Register Now",
                modifier = Modifier
                    .background(color = Color.Red   )
                    .clickable {  },
                textDecoration = TextDecoration.Underline
            )
        }

    }
}