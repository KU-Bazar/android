package com.ku.bazar.login.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.R

@Composable
fun logo(
    modifier: Modifier
){
    Box(modifier = Modifier){

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
    }
}

@Composable
fun loginButton(modifier : Modifier){
    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(color = Color.Red)
            .clickable {  }
    ){
        Text(
            text = "Login",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun registerButton(
    modifier : Modifier
){
    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(color = Color.White)
            .border(width = 10.dp, color = Color.Red)
            .clickable {  }
    ){
        Text(
            text = "Register",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            textAlign = TextAlign.Center
        )
    }
}