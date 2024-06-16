package com.ku.bazar.chat.models

import com.google.gson.annotations.SerializedName

data class SocketMessage(
    @SerializedName("sender_id") val senderId: String?,
    @SerializedName("receiver_id") val receiverId: Int?,
    @SerializedName("content") val content: String?,
)

