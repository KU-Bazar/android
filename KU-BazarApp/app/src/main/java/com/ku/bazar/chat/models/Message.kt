package com.ku.bazar.chat.models

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("message_id") val messageId: Int,
    @SerializedName("chat_id") val chatId: Int?,
    @SerializedName("sender_id") val senderId: String?,
    @SerializedName("receiver_id") val receiverId: String?,
    @SerializedName("content") val content: String,
    @SerializedName("sent_at") val sentAt: String?, // Storing time as String
    @SerializedName("seen") val seen: Boolean?
)
