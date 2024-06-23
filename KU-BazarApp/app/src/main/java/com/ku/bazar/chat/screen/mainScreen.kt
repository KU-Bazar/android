package com.ku.bazar.chat.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ku.bazar.chat.ApiInterface
import com.ku.bazar.chat.components.conversationBox
import com.ku.bazar.chat.components.mainAppBar
import com.ku.bazar.chat.models.Conversation
import com.ku.bazar.mainScreen.components.AppBottomNav
import com.ku.bazar.mainScreen.data.MainScreen
import com.ku.bazar.navigation.Screen
import com.ku.bazar.ui.theme.PrimaryPink
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://special-bunny-vigorously.ngrok-free.app/"
@Composable
fun mainScreen(id:String,navController: NavHostController) {
    val conversationList = remember { mutableStateListOf<Conversation>() }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        getConversationData(id) { conversations ->
            conversationList.clear()
            conversationList.addAll(conversations)
            isLoading=false
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.padding(bottom = 56.dp),
            topBar = {
                mainAppBar()
            },
            containerColor = Color.Transparent
        ) { paddingValues ->
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = PrimaryPink)
                }

            } else {

                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues = paddingValues)
                        .padding(horizontal = 16.dp)
                        .fillMaxSize()
                ) {
                    items(conversationList) { conversation ->
                        conversationBox(
                            conversation,
                            onClick = { navController.navigate( Screen.Conversation.createRoute(id,conversation.id))})
                    }
                }
            }
        }
    }
}
private fun getConversationData(id: String, onConversationDataFetched: (List<Conversation>) -> Unit) {
    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiInterface::class.java)
    val retrofitData = retrofitBuilder.getConversations(id)
    retrofitData.enqueue(object : Callback<List<Conversation>> {
        override fun onResponse(
            call: Call<List<Conversation>>,
            response: Response<List<Conversation>>
        ) {
            if (response.isSuccessful) {
                val conversations = response.body() ?: emptyList()
                onConversationDataFetched(conversations)
            } else {
                Log.e("CHAT", "Failed to fetch conversation data: ${response.message()}")
            }
        }
        override fun onFailure(call: Call<List<Conversation>>, t: Throwable) {
            Log.e("CHAT", "Failed to fetch conversation data: ${t.message}")
        }
    })
}