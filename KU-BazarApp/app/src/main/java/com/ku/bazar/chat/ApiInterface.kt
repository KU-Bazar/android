package com.ku.bazar.chat

import com.ku.bazar.chat.models.Conversation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("conversations/{id}")
    fun getConversations(@Path("id") id: String): Call<List<Conversation>>
}