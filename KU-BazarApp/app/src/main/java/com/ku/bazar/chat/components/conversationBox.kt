package com.ku.bazar.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ku.bazar.chat.models.Conversation
import com.ku.bazar.ui.theme.TextBlack



@Composable
fun conversationBox(conversation: Conversation, onClick: () -> Unit){
    Row(
        modifier = Modifier
            .padding(vertical = 22.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick= onClick) //left to fill
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            AsyncImage(model = conversation.avatarUrl, contentDescription =null, contentScale = ContentScale.Crop )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .height(60.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = conversation.fullName, //left to fill
                    maxLines = 1,
                    modifier = Modifier.weight(1f),
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        color = TextBlack,
                        fontSize = 18.sp,
                    )
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = conversation.lastMessageSentAt.toString(), //left to fill
                    style = TextStyle(
                        color = TextBlack,
                        fontSize = 16.sp,
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = conversation.lastMessage.toString(), //left to fill
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        color = TextBlack,
                        fontSize = 16.sp,
                    )
                )

                Spacer(modifier = Modifier.width(10.dp))
                if(conversation.unseenMessagesCount!=0)
                ReadIndicator()

            }
        }
    }
}

@Composable
fun ReadIndicator() {
    Box(
        modifier = Modifier
            .size(12.dp)
            .clip(CircleShape)
            .background(Color(0xFF007EF4))
    )
}