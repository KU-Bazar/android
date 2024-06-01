package com.ku.bazar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding

import androidx.compose.material.Button
import androidx.compose.material.Text

import androidx.compose.ui.Alignment

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.foundation.background

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Preview(showSystemUi =true, showBackground = true)

@Composable
fun MyApp() {
    MaterialTheme {
        Scaffold(
            bottomBar = { BottomNavigationBar() },
            content = { paddingValues ->
                MainScreen(Modifier.padding(paddingValues))
            }
        )
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopBar()
        SearchBar()
        CategoriesSection()
        ProductSection(sectionTitle = "Recently Added")
        PopularProductSection(sectionTitle = "Popular Items ")


    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* TODO: Handle menu click */ }) {
            Icon(painter = painterResource(id = R.drawable.ci_hamburger), contentDescription = "Menu", modifier = Modifier.size(32.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Hi, User👋",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(36.dp)
                .background(Color.Gray, CircleShape)
        )
    }
}

@Composable
fun SearchBar() {

    var searchText by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)

    ) {
        Text(
            text = "Find Desired Products Near" ,
            modifier = Modifier.padding(bottom = 4.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Kathmandu University",
            modifier = Modifier.padding(bottom = 8.dp), // This line adds some space between this text and the search bar.
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )

        OutlinedTextField(
            value = searchText,
            onValueChange = { newText -> searchText = newText },
            placeholder = { Text(text = "Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(20.dp)
                )
            },
            shape = RoundedCornerShape(25.dp)
        )
    }
}

@Composable
fun CategoriesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Categories", fontWeight = FontWeight.Bold)

        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp) //  This line is  adds spacing between items
        ) {
            val categories = listOf("Chair", "Books & Notes", "Instruments", "Electronics", "Clothes")
            items(categories.size) { index ->
                CategoryItem(name = categories[index])
            }
        }
    }
}

@Composable
fun CategoryItem(name: String) {
    Button(
        onClick = { /* TODO: Handle category click */ },
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .width(150.dp)
            .size(45.dp) // Set the size of the round button
            .padding(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF33AA), contentColor = Color.White)
    ) {
        Text(
            text = name,
            fontSize = 12.sp, // Adjust text size to fit inside the round button
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun ProductSection(sectionTitle: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = sectionTitle, fontWeight = FontWeight.Bold)

        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2), // can be wriiten adaptive insted of fixed allocate dynamically
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 0.dp)
        ) {
            items(10) { index ->
                val (imageId, productTitle) = when (index) {
                    0 -> R.drawable.ic_guitar to " Acoustic Guitar"
                    1 -> R.drawable.ic_cable to "HDMI2 Cable + TV"
                    2 -> R.drawable.ic_guitar to " Acoustic Guitar"
                    3 -> R.drawable.ic_cable to "HDMI2 Cable + TV"
                    else -> R.drawable.ic_error to "Placeholder"
                }
                ProductItem(imageId = imageId, title = productTitle)
            }
        }
    }
}


@Composable
//This function is not showing in the Ui have to modify
fun PopularProductSection(sectionTitle: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = sectionTitle, fontWeight = FontWeight.Bold)
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 0.dp)
        ) {
            items(10) { index ->
                val (imageId, productTitle) = when (index) {
                    0 -> R.drawable.ic_guitar to "Popular Acoustic Guitar"
                    1 -> R.drawable.ic_cable to "Popular HDMI2 Cable + TV"
                    2 -> R.drawable.ic_guitar to "Popular Acoustic Guitar"
                    3 -> R.drawable.ic_cable to "Popular HDMI2 Cable + TV"
                    else -> R.drawable.ic_error to "Placeholder"
                }
                ProductItem(imageId = imageId, title = productTitle)
            }
        }
    }
}



@Composable
fun ProductItem(imageId: Int, title: String) {
    val isFavourite= remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            horizontalAlignment =  Alignment.CenterHorizontally) {
            Box(modifier = Modifier.fillMaxWidth())
            {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .fillMaxWidth()
                        .height(120.dp)
                )
                IconButton(onClick = { isFavourite.value = !isFavourite.value },
                    modifier = Modifier.
                    align(Alignment.TopEnd)
                ) {

                    Icon(painter = painterResource(id = if(isFavourite.value)R.drawable.ic_favourite_filled else R.drawable.ic_favourite_unfill),
                        contentDescription = "Favorite Icon" ,
                        modifier = Modifier.size(15.dp)  )
                    
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold)
                Text(text = "Rs. 1800/day")
                Button(
                    onClick = { /* TODO: Handle buy now click */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF33AA),
                            contentColor = Color.White),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = "Buy Now" )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    BottomNavigation(
        backgroundColor =  MaterialTheme.colors.primary
    ) {
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_home), contentDescription = "Home",modifier=Modifier.size(20.dp)) },
            label = { Text(text = "Home") },
            selected = true,
            onClick = { /* TODO: Handle Home click */ }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_heart), contentDescription = "Favorites",modifier=Modifier.size(20.dp)) },
            label = { Text(text = "Favorites") },
            selected = false,
            onClick = { /* TODO: Handle Favorites click */ }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_cart), contentDescription = "Cart",modifier=Modifier.size(20.dp)) },
            label = { Text(text = "Cart") },
            selected = false,
            onClick = { /* TODO: Handle Cart click */ }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.chat), contentDescription = "Profile",modifier=Modifier.size(20.dp)) },
            label = { Text(text = "Chat") },
            selected = false,
            onClick = { /* TODO: Handle Profile click */ }
        )
    }
}
















































