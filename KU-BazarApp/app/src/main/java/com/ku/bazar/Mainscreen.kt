package com.ku.bazar

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.ku.bazar.ui.theme.Purple700


@Preview(showBackground =false ,  showSystemUi =true)
@Composable
fun HomeScreen() {

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = "Hi, Bipul",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            },
            backgroundColor = MaterialTheme.colors.primary,
            actions = {
                IconButton(onClick = {  }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
            }
        )
        Surface(color = Color.White) {
            Column(modifier = Modifier.padding(50.dp)) {
                    Text(
                        text = "Find Desired Products Near",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Kathmandu University",

                        fontWeight = FontWeight.Bold,
                        color = Color.Black)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp)
                ) {
                    Text(
                        text = "Categories",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))

                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.material_symbols_chair),
                        contentDescription = "Chair",
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(modifier = Modifier
                        .width(1.dp)
                        .height(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Chair", color = Color.Black)
                        Text(text = "Rs. 1000", color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)) {
                        Text(text = "Buy Now")
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Recently Added",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "See all", color = Color.Gray)
                }
                // Add more product listings here following the same structure
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_guitar),
                        contentDescription = "Acoustic Guitar",
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Rent Acoustic Guitar", color = Color.Black)
                        Text(text = "Rs. 1800/day", color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {  }) {
                        Text(text = "Rent Now")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Popular Products",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "See all")


                }
            }
        }
    }
}




