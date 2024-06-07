package com.ku.bazar.chat.screen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ku.bazar.BASE_URL
import com.ku.bazar.chat.ApiInterface
import com.ku.bazar.chat.components.conversationBox
import com.ku.bazar.chat.components.mainAppBar
import com.ku.bazar.chat.models.Conversation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun mainScreen() {
    val conversationList = remember { mutableStateListOf<Conversation>() }
    LaunchedEffect(key1 = true) {
        getConversationData("4e3b5e7a-93e1-4f8b-9c1c-5b6d7e8a2d4f") { conversations ->
            conversationList.clear()
            conversationList.addAll(conversations)
        }
    }
    Scaffold(
        topBar = {
            mainAppBar()
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            items(conversationList) { conversation ->
                conversationBox(conversation)
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