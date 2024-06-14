package com.ku.bazar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ku.bazar.chat.ApiInterface
import com.ku.bazar.chat.components.ChatAppNavGraph
import com.ku.bazar.chat.models.Conversation
import com.ku.bazar.chat.screen.chatScreen
import com.ku.bazar.chat.screen.mainScreen
import com.ku.bazar.login.screen.register
import com.ku.bazar.ui.theme.KUBazarTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ChatAppNavGraph(navController = navController)
            KUBazarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ChatAppNavGraph(navController = navController)
//                    chatScreen( "d9b2d63d-a233-4123-847a-7ac9b47c4f44","4e3b5e7a-93e1-4f8b-9c1c-5b6d7e8a2d4f")
//                    mainScreen(BASE_USER)
//                    MyApp()


                }
            }
        }
    }


}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    KUBazarTheme {
        Greeting("Android")
    }
}
