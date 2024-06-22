package com.ku.bazar.mainScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.R
import com.ku.bazar.ui.theme.PrimaryPink
import com.ku.bazar.ui.theme.TextBlack

@Composable
fun CategoriesSection() {
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal =24.dp)
            .fillMaxWidth()

    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Categories", fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(bottom = 10.dp))
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Adjust spacing as needed
            ) {
                val categories = listOf(
                    Pair("Furniture", R.drawable.ic_furniture),
                    Pair("Clothing ", R.drawable.ic_clothing),
                    Pair("Books", R.drawable.ic_book_photo),
                    Pair("Electronics", R.drawable.ic_electronics),
                    Pair("Other", R.drawable.ic_other)
                )
                items(categories.size) { index ->
                    CategoryItem(name = categories[index].first, imageResId = categories[index].second)
                }
            }
        }
    }
}



@Composable
fun CategoryItem(name: String, imageResId: Int) {

    Box(
        modifier= Modifier
            .width(130.dp)
            .height(40.dp)
            .padding(start = 5.dp)
            .background(
                color = Color(0xFFF5F5F5),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { }
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Category Icon",
                modifier = Modifier
                    .size(24.dp) // Adjust size as needed
                    .clip(CircleShape)
                    .background(PrimaryPink)
                    .padding(4.dp), // Adjust padding as needed
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = TextBlack
            )
        }
    }
}