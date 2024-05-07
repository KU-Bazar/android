package com.ku.bazar.util

import androidx.annotation.DrawableRes
import com.ku.bazar.R

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
    val plant : Int
){
    object first : login(
        first_background = R.drawable.login_background,
        cloud_first = ,
        cloud_second = ,
        cloud_third = ,
        house = ,
        plant =
    )
}