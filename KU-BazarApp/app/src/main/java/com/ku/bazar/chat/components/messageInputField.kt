package com.ku.bazar.chat.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.ui.theme.White


@Composable
fun messageInputField(sendMessage: (String) -> Unit) {
    val message = remember { mutableStateOf("") }

    TextField(
        value = message.value,
        onValueChange = {
            message.value = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .navigationBarsPadding()
            .imePadding(),
        textStyle = TextStyle(
            color = Color(0xFFACACAC),
            fontSize = 16.sp
        ),
        placeholder = {
            Text(
                text = "Type a message...",
                style = TextStyle(
                    color = Color(0xFFACACAC),
                    fontSize = 16.sp
                )
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {sendMessage(message.value) },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color(0xFFACACAC)
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Send,
                    contentDescription = null

                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        shape = RoundedCornerShape(50),

     colors = TextFieldDefaults.colors(
        disabledContainerColor = Color(0xFF6B6B6B),
        focusedContainerColor = Color(0xFFF0F0F0),
        unfocusedContainerColor = Color(0xFFF1F1F1),
        errorContainerColor =  Color(0xFF2B2B2B),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
    )
}
