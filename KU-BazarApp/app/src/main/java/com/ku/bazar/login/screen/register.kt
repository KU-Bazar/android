package com.ku.bazar.login.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.R
import com.ku.bazar.login.component.loginBackground
import com.ku.bazar.login.component.registerBigButton
import com.ku.bazar.login.util.loginMain
import androidx.navigation.NavHostController
import com.ku.bazar.login.data.RegisterRequest
import com.ku.bazar.login.data.RetrofitClient
import com.ku.bazar.login.data.User
import com.ku.bazar.navigation.Screen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun register(navHostController: NavHostController){

    var username by remember {
        mutableStateOf("")
    }

    var fullname by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var user: User? = null

    var isLoading by remember { mutableStateOf(false) }

    var message by remember { mutableStateOf("") }

    val url = "https://humorous-admittedly-mongoose.ngrok-free.app/"

    val context = LocalContext.current

    val DEFAULT_IMAGE_URL = "https://picsum.photos/200/300"

    fun registerUser() {
        isLoading = true
        val request = RegisterRequest(email, username)
        RetrofitClient.instance.registerUser(request).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                isLoading = false
                if (response.isSuccessful) {
                    user = response.body()
                    val imageUrl = user?.imageURL ?: DEFAULT_IMAGE_URL
                    message = "Registration successful"
                } else {
                    message = "Registration failed: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                isLoading = false
                message = "Error: ${t.localizedMessage}"
            }
        })
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
                text = loginMain.second.title,
                fontSize = 27.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row() {
                loginMain.second.fullName?.let { Text(text = it , fontWeight = FontWeight.Bold) }

                Spacer(modifier = Modifier.padding(horizontal = 5.dp))

                loginMain.second.username?.let { Text(text = it , fontWeight = FontWeight.Bold) }
            }

            Spacer(modifier = Modifier.height(2.dp))

            Row() {
                TextField(
                    value = fullname,
                    onValueChange = {fullname = it},
                    label = {
                        Text(text = "Full Name")
                    },
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .size(height = 40.dp, width = 140.dp)
                )

                Spacer(modifier = Modifier.padding(horizontal = 5.dp))

                TextField(
                    value = username,
                    onValueChange = {username = it},
                    label = {
                        Text(text = "Username")
                    },
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .size(height = 40.dp, width = 140.dp)
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            loginMain.second.email?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            TextField(
                value = email,
                onValueChange = {email = it},
                label = {
                    Text(text = "E-mail")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .background(color = Color.Gray)
                    .size(height = 40.dp, width = 280.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = loginMain.second.password,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(2.dp))

            TextField(
                value = password,
                onValueChange = {password = it},
                label = {
                    Text(text = "Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password ),
                modifier = Modifier
                    .background(color = Color.Gray)
                    .size(height = 40.dp, width = 280.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

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

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
            ){
                Image(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.padding(horizontal = 3.dp))

                Text(text = loginMain.second.oauth)
            }

            Spacer(modifier = Modifier.height(20.dp))

            registerBigButton(
                modifier = Modifier, onClick = {
                    registerUser()
                    navHostController.navigate(Screen.Home.route)
                    }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Text(
                    text = loginMain.second.account,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(9.dp))

                Text(
                    text = "Login Now",
                    color = Color.Red,
                    modifier = Modifier
                        .clickable { },
                    textDecoration = TextDecoration.Underline
                )
            }

        }
    }
}



