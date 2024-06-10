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
import com.ku.bazar.chat.ApiInterface
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

const val BASE_URL = "https://6cea-2404-7c00-49-daf2-f6db-fe52-ffb3-13f5.ngrok-free.app/"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KUBazarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
           //         chatScreen()
//                    mainScreen()
//                    MyApp()
//                    getConversationData("4e3b5e7a-93e1-4f8b-9c1c-5b6d7e8a2d4f")
register()
                }
            }
        }
    }
//    private fun getConversationData(id: String) {
//        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
//            BASE_URL).build().create(ApiInterface::class.java)
//        val retrofitData = retrofitBuilder.getConversations(id)
//        retrofitData.enqueue(object : Callback<List<Conversation>> {
//            override fun onResponse(
//                call: Call<List<Conversation>>,
//                response: Response<List<Conversation>>
//            ) {
//                if (response.isSuccessful) {
//                    val conversations = response.body() ?: emptyList()
//                    for (conversation in conversations) {
//                        Log.d("CHAT", conversation.toString())
//                    }
//                } else {
//                    Log.e("CHAT", "Failed to fetch conversation data: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<List<Conversation>>, t: Throwable) {
//                Log.e("CHAT", "Failed to fetch conversation data: ${t.message}")
//            }
//        })
//    }
//

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
