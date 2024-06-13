package com.ku.bazar.login.util

import androidx.annotation.DrawableRes
import androidx.compose.ui.text.AnnotatedString
import com.ku.bazar.R

val stringObject = String()
sealed class login(
    @DrawableRes
    val first_background : Int,
    @DrawableRes
    val cloud_first : Int,
    @DrawableRes
    val cloud_second : Int,
    @DrawableRes
    val cloud_third : Int,
    @DrawableRes
    val house : Int,
    @DrawableRes
    val plant : Int,

    val title : AnnotatedString,

    val description : AnnotatedString
){
    object main : login(
        first_background = R.drawable.login_background,
        cloud_first = R.drawable.cloud_first,
        cloud_second = R.drawable.clock_second,
        cloud_third = R.drawable.cloud_third,
        house = R.drawable.house,
        plant =R.drawable.plant,
        title = stringObject.loginHeading,
        description = stringObject.loginDescription
    )
}

sealed class loginMain(
    val title: AnnotatedString,
    val fullName : kotlin.String?,
    val username : kotlin.String?,
    val email : kotlin.String?,
    val password : kotlin.String,

    val oauth : kotlin.String,
    val account : kotlin.String
        ){

    object first : loginMain(
        title = stringObject.titleLogin,
        fullName = "Full Name",
        username = "Username/Email",
        email = "Email",
        password = "Password",
        oauth = "Login via google",
        account = "Don't have an account?"
    )

    object second : loginMain(
        title = stringObject.titleLogin,
        fullName = "Full Name",
        username = "Username",
        email = "Email",
        password = "Password",
        oauth = "Login via google",
        account = "Already have an account?"
    )

}