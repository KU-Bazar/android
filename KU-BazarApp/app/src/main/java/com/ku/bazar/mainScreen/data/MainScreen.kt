package com.ku.bazar.mainScreen.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ku.bazar.R

sealed class MainScreen(
    val route: String,
    @StringRes val title: Int? = null,
    @DrawableRes val icon: Int? = null
) {
    object Home : MainScreen("home", R.string.home, R.drawable.ic_home2)
    object Favorite : MainScreen("favorite", R.string.favorite, R.drawable.ic_favourite_filled)
    object Chat : MainScreen("chat", R.string.chat, R.drawable.ic_chat2)
    object Profile : MainScreen("profile", R.string.profile, R.drawable.ic_settings)
    object Cart : MainScreen("cart") // No title or icon for the cart, as it's handled differently
}