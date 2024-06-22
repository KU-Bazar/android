package com.ku.bazar.navigation

import android.app.Application
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ku.bazar.login.screen.login
import com.ku.bazar.login.screen.loginFirst
import com.ku.bazar.login.screen.register
import com.ku.bazar.mainScreen.HomeScreen
import com.ku.bazar.productpage.Description

import com.ku.bazar.mainScreen.viewModel.FavoriteItemsViewModel
import com.ku.bazar.mainScreen.viewModel.HomeViewModel
import com.ku.bazar.onBoarding.component.OnBoardingViewModel
import com.ku.bazar.onBoarding.model.OnBoardingViewModelFactory
import com.ku.bazar.onBoarding.onBoarding
import com.ku.bazar.productListing.models.Category
import com.ku.bazar.productListing.screen.mainProdcutListingScreen

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onBoarding")
    object LoginScreen : Screen("loginScreen")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Sell : Screen("sell")
    object Description : Screen("description/{productId}") {
        fun createRoute(productId: Int) = "description/$productId"
    }}

@Composable
fun Nav() {
    val navController = rememberNavController()
    val viewModel: OnBoardingViewModel = viewModel(
        factory = OnBoardingViewModelFactory(
            LocalContext.current.applicationContext as Application
        )
    )
    val homeViewModel = HomeViewModel()
    val favoriteItemsViewModel = FavoriteItemsViewModel()
    val userName = "Bipul"

    Surface(color = MaterialTheme.colors.background) {
        if (viewModel.isFirstTime.value) {
            NavHost(navController = navController,
                startDestination = Screen.OnBoarding.route)
            {

                composable(Screen.OnBoarding.route) {
                    onBoarding(navController, viewModel)
                }

                composable(Screen.LoginScreen.route) {
                    loginFirst(navController)
                }

                composable(Screen.Login.route) {
                    login(navController)
                }

                composable(Screen.Register.route) {
                    register(navController)
                }
                composable(Screen.Description.route) { backStackEntry ->
                    val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
                    productId?.let {
                        Description(navController, it)
                    }
                }

                composable(Screen.Home.route) {
                    HomeScreen(
                        navController,
                        homeViewModel,
                        favoriteItemsViewModel,
                        userName,
                        navController
                    )
                }

                composable(Screen.Sell.route){
                    mainProdcutListingScreen(category = Category.OTHER)
                }
            }
        }
        else {
            NavHost(navController = navController,
                startDestination = Screen.LoginScreen.route){
                composable(Screen.OnBoarding.route) {
                    onBoarding(navController, viewModel)
                }

                composable(Screen.LoginScreen.route) {
                    loginFirst(navController)
                }

                composable(Screen.Login.route) {
                    login(navController)
                }

                composable(Screen.Register.route) {
                    register(navController)
                }
                composable(Screen.Home.route) {
                    HomeScreen(
                        navController,
                        homeViewModel,
                        favoriteItemsViewModel,
                        userName,
                        navController
                    )
                }

                composable(Screen.Sell.route){
                    mainProdcutListingScreen(category = Category.OTHER)
                }
            }
        }
    }
}