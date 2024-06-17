package com.ku.bazar.chat.models
import com.google.gson.annotations.SerializedName

data class SocketOnChatConnection(
    @SerializedName("sender_id") val SenderId: String,
    @SerializedName("receiver_id") val ReceiverId: String,

)
