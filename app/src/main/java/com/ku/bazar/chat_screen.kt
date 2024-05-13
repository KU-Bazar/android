package com.ku.bazar
import androidx.compose.foundation.Image
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ku.bazar.ui.theme.Dark100
import com.ku.bazar.ui.theme.Dark500
import com.ku.bazar.ui.theme.Pink300
import com.ku.bazar.ui.theme.Pink500
import com.ku.bazar.ui.theme.Pink700
import com.ku.bazar.ui.theme.Purple200

@Composable
fun ChatScreen(){
Column {
    ChatHeader()
    ChatCardContainer()
}
}
@Composable
fun ChatHeaderNotify(number:Int) {
    Box(
        Modifier
            .clip(shape = RoundedCornerShape(100.dp))
            .background(color = Color.White)
            .padding(5.dp)

        ) {
        Text(text = number.toString(), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, color= Dark100)
    }
}
@Composable
fun ChatHeader() {
        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 10.dp))
            .fillMaxWidth()
            .background(color = Pink500)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 40.dp, bottom = 5.dp)
                ) {
                    Text(
                        text = "Messages",
                        color = Color.White,
                        fontSize = 30.sp,
                        style = MaterialTheme.typography.h1
                    )
                    ChatHeaderNotify(1)
                }

                TextField(value = "Search Previous Messages",onValueChange = {onSearchValueChange()},shape = RoundedCornerShape(100.dp),colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Pink700,
                    focusedIndicatorColor = Pink500,
                    unfocusedIndicatorColor = Pink500,
                    textColor = Pink300,


                ) ,modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 25.dp)
                    ,leadingIcon={ Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Pink300) } )
            }
        }
}
fun onSearchValueChange(){

}



@Composable
fun ChatCardContainer() {
    Box()
    {
        val bn_cyan= painterResource(id = R.drawable.bn_cyan);
        val bn_pink= painterResource(id = R.drawable.bn_pink);
        val bn_purple = painterResource(id = R.drawable.bn_purple);
        val bn_lime = painterResource(id = R.drawable.bn_lime);
        Column {
            MessengerChatCard(profilePicture = bn_cyan, name = "Bipul Lamsal", lastMessage ="How are yoi doing???? . 2 mins ago" )
            MessengerChatCard(profilePicture = bn_pink, name = "Nishant Pandit", lastMessage ="brother, please buy . yesterday " )
            MessengerChatCard(profilePicture = bn_purple, name = "Neer Aryan", lastMessage ="why??? . 21 days ago" )
            MessengerChatCard(profilePicture = bn_lime, name = "Utkrist Neuoane", lastMessage ="Okay . a month ago" )

        }
    }
}
@Composable
fun MessengerChatCard(
    profilePicture: Painter,
    name: String,
    lastMessage: String
) {
    Surface(
        modifier = Modifier.padding(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = profilePicture,
                contentDescription = "Profile Picture",
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(100.dp)),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Dark500
                )
                Text(
                    text = lastMessage,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Dark100
                )
            }
        }
    }
}

