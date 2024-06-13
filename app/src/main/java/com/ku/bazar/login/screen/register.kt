package com.ku.bazar.login.screen

import android.util.Log
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
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.ku.bazar.R
import com.ku.bazar.login.component.loginBackground
import com.ku.bazar.login.component.registerBigButton
import com.ku.bazar.login.util.loginMain
import com.ku.bazar.login.data.googleSignIn
import com.ku.bazar.login.data.rememberOneTapSignInState
import com.ku.bazar.login.viewModel.SignInViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.remember as remember

@Composable
fun register(){

    var SERVER_CLIENT_ID = "935709459274-lek0lv3cftsr8uu89u0hpf3j45b22k85.apps.googleusercontent.com"

    //val clientID = BuildConfig.SERVER_CLIENT_ID
    //Log.i("tag", clientID)

    val state = rememberOneTapSignInState()
    val viewModel: SignInViewModel = viewModel()

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

    loginBackground()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(x = 60.dp, y = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = loginMain.second.title,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row() {
            loginMain.second.fullName?.let { Text(text = it , fontWeight = FontWeight.Bold) }

            Spacer(modifier = Modifier.height(20.dp))

            loginMain.second.username?.let { Text(text = it , fontWeight = FontWeight.Bold) }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row() {
            TextField(
                value = fullname,
                onValueChange = {fullname = it},
                label = {
                    Text(text = "Full Name")
                },
                modifier = Modifier.background(color = Color.Gray)
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = username,
                onValueChange = {username = it},
                label = {
                    Text(text = "Username")
                },
                modifier = Modifier.background(color = Color.Gray)
            )

        }

        Spacer(modifier = Modifier.height(20.dp))

        loginMain.second.email?.let {
            Text(
                text = it,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = email,
            onValueChange = {email = it},
            label = {
                Text(text = "E-mail")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.background(color = Color.Gray)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = loginMain.second.password,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = password,
            onValueChange = {password = it},
            label = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password ),
            modifier = Modifier.background(color = Color.Gray)
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

        val scope = rememberCoroutineScope()
        val context = LocalContext.current

        Row(
            modifier = Modifier
                .clickable {
                    Log.d("1","1")
                    scope.launch {
                        Log.d("2","2")
                        viewModel.googleSignIn(
                            context = context,
                            state = state,
                            clientId = SERVER_CLIENT_ID,
                            rememberAccount = false
                        )
                    }
            }
        ){
            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = null
            )

            Text(text = loginMain.second.oauth)
        }

        Spacer(modifier = Modifier.height(30.dp))

        registerBigButton(modifier = Modifier.clickable {  })

        Spacer(modifier = Modifier.height(30.dp))

        Row {
            Text(
                text = loginMain.second.account,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(9.dp))

            Text(
                text = "Login Now",
                modifier = Modifier
                    .background(color = Color.Red)
                    .clickable { },
                textDecoration = TextDecoration.Underline
            )
        }

    }
}