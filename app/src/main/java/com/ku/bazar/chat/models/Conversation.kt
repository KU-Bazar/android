package com.ku.bazar.chat.models

import com.google.gson.annotations.SerializedName

data class Conversation(
    @SerializedName("id") val id: String,
    @SerializedName("fullname") val fullName: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("last_message") val lastMessage: String?,
    @SerializedName("last_message_sent_at") val lastMessageSentAt: String?,
    @SerializedName("last_message_sender_id") val lastMessageSenderId: String?,
    @SerializedName("unseen_messages_count") val unseenMessagesCount: Int
)
