package com.ku.bazar.login.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
            .width(400.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = 40.dp, y = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Register into KU-Bazar",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextField(
                value = fullname,
                onValueChange = { fullname = it },
                label = { Text(text = "Full Name") },
                placeholder = { Text(text = "Neery Aryan Bhatt") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray, shape = MaterialTheme.shapes.small)
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                placeholder = { Text(text = "neern2552") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray, shape = MaterialTheme.shapes.small)
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                placeholder = { Text(text = "neern2552@gmail.com") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray, shape = MaterialTheme.shapes.small)
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "**************") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray, shape = MaterialTheme.shapes.small)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                )
                Text(
                    text = "or",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://humorous-admittedly-mongoose.ngrok-free.app/"))
                        context.startActivity(intent)
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Login via Google",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            registerBigButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    registerUser()
                    navHostController.navigate(Screen.Home.route)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Already have an account?",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Login Now",
                    color = Color.Red,
                    modifier = Modifier.clickable { /* Navigate to login screen */ },
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}



