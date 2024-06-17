package com.ku.bazar.mainScreen.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ku.bazar.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int? = null,
    @DrawableRes val icon: Int? = null
) {
    object Home : Screen("home", R.string.home, R.drawable.ic_home_empty)
    object Favorite : Screen("favorite", R.string.favorite, R.drawable.ic_favourite_unfill)
    object Chat : Screen("chat", R.string.chat, R.drawable.ic_chat2)
    object Profile : Screen("profile", R.string.profile, R.drawable.ic_chat2)
    object Cart : Screen("cart") // No title or icon for the cart, as it's handled differently
}