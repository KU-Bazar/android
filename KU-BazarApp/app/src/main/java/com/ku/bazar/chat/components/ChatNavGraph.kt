package com.ku.bazar.chat.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ku.bazar.chat.screen.chatScreen
import com.ku.bazar.chat.screen.mainScreen

const val BASE_USER ="4e3b5e7a-93e1-4f8b-9c1c-5b6d7e8a2d4f"
@Composable
fun ChatAppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            mainScreen(BASE_USER,navController)
        }
        composable("chatScreen/{senderId}/{receiverId}") { backStackEntry ->
            val senderId = backStackEntry.arguments?.getString("senderId") ?: ""
            val receiverId = backStackEntry.arguments?.getString("receiverId") ?: ""
            chatScreen(senderId, receiverId)
        }
    }
}
