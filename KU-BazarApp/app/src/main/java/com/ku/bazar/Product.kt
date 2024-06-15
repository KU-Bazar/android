package com.ku.bazar

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.Spacer


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.ui.theme.PrimaryPink

@Composable

fun Product(scrollState: ScrollState) {

    Box(modifier = Modifier.fillMaxSize())
    {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = -300.dp),


            )

        {


            Image(
                painter = painterResource(id = R.drawable.login_background),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 45.dp,
                            bottomEnd = 45.dp
                        )
                    )
            )

        }
        Box(
            modifier = Modifier.fillMaxSize()
                .offset(y=30.dp),
            contentAlignment = Alignment.TopCenter
        )
        {
            Text(
                text = "Add Product",
                fontSize = 20.sp,
                color = Color.White


            )

        }







            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 90.dp, x = 120.dp)

            )
            {
                Image(
                    painter = painterResource(id = R.drawable.topographic_6),
                    contentDescription = "Pattern",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()


                )

            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
                    .offset(y = 20.dp, x = 20.dp)

            )

            {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable { }
                        .background(color = Color(0xFFF5F5F5))
                        .padding(10.dp),
                    tint = Color.Black
                )
            }

            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(320.dp)

                    .align(alignment = Alignment.Center)
                    .offset(y = -130.dp)
                    .background(color = Color(0xFFD9D9D9))
                    .clip(

                        RoundedCornerShape(
                            topStart = 200.dp,
                            topEnd = 50.dp,
                            bottomEnd = 50.dp,
                            bottomStart = 50.dp

                        )
                    ),

                contentAlignment = Alignment.Center

            )
            {
                Text(
                    text = "Image Preview",
                    color = Color(0xFFB1B1B1)
                )

            }





            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 340.dp, x = 20.dp)


            ) {
                Spacer(
                    modifier = Modifier.height(75.dp)
                )
                SearchBarWithProductName("Product Name")
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 430.dp, x = 20.dp)


            ) {
                Spacer(
                    modifier = Modifier.height(75.dp)
                )
                SearchBarWithProductName("Price")
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 520.dp, x = 20.dp)


            ) {
                Spacer(
                    modifier = Modifier.height(75.dp)
                )
                SearchBarWithProductName("Description")
            }



            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 700.dp, x = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {


                Button(
                    onClick = { /* TODO: Handle buy now click */ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryPink,
                        contentColor = Color.White,


                        ),
                    shape = RoundedCornerShape(10.dp),

                ) {
                    androidx.compose.material.Text(
                        text = "Add Image",
                        modifier = Modifier.padding(end = 12.dp)
                    )

                }

                Button(
                    onClick = { /* TODO: Handle buy now click */ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = PrimaryPink,

                        ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier


                )
                {
                    androidx.compose.material.Text(
                        text = "Upload Image",
                        modifier = Modifier.padding(end = 12.dp)
                    )

                }


            }


    }
}

@Composable
fun SearchBarWithProductName(
    title:String

) {

    var searchText by remember { mutableStateOf(TextFieldValue("")) }


    Column(
        modifier = Modifier
            .width(350.dp)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color(0xFFB1B1B1),

            modifier = Modifier.padding(bottom = 10.dp)
        )
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("") },
            modifier = Modifier
                .fillMaxWidth(),

            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF5F5F5),
                focusedIndicatorColor = PrimaryPink,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}





