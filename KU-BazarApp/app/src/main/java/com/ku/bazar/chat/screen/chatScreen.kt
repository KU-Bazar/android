package com.ku.bazar.chat.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.ku.bazar.chat.components.chatAppBar
import com.ku.bazar.chat.components.messageInputField
import com.ku.bazar.chat.models.SocketOnChatConnection
import io.socket.client.IO
import io.socket.client.Socket
import com.google.gson.Gson
import com.ku.bazar.chat.components.messageBox
import com.ku.bazar.chat.models.Message
import com.ku.bazar.ui.theme.PrimaryPink
import org.json.JSONArray
import org.json.JSONObject



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun chatScreen(SenderId:String, ReceiverId:String){

    lateinit var socket: Socket
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)
    socket = IO.socket("https://special-bunny-vigorously.ngrok-free.app/connect")
    socket.connect()

    val chatConnection = SocketOnChatConnection(SenderId, ReceiverId)
    val json = Gson().toJson(chatConnection)
    socket.emit("join_chat",JSONObject(json))

    val messages = remember { mutableStateListOf<Message>() }
    var isLoading by remember { mutableStateOf(true) }
    socket.on("chat-history") { args ->
        val history = args[0] as JSONArray
        val newMessages = (0 until history.length()).map { i ->
            val messageObject = history.getJSONObject(i)
            Gson().fromJson(messageObject.toString(), Message::class.java)
        }

        messages.addAll(newMessages)
        isLoading=false
    }
    LaunchedEffect(messages) {
        socket.on("private-chat") { args ->
            val messageObject = args[0] as JSONObject
            val message = Gson().fromJson(messageObject.toString(), Message::class.java)
            messages.add(0,message)
        }
    }

    val sendMessage: (String) -> Unit = { messageContent ->
        val messageData = JSONObject().apply {
            put("sender_id", SenderId)
            put("receiver_id", ReceiverId)
            put("content", messageContent)
        }
        socket.emit("message", messageData)
    }

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.ime),
        topBar = {
            chatAppBar()
        },
        containerColor = Color.Transparent,
    ){
            paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = PrimaryPink)
                }
            }else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    reverseLayout = true,
                ) {
                    items(messages) { message ->
                        messageBox(userId = SenderId, message = message)
                    }
                }
            }
            messageInputField(sendMessage)
        }
    }
}


