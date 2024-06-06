package com.ku.bazar

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ku.bazar.ui.theme.PrimaryPink
import com.ku.bazar.ui.theme.SecondaryPink
import com.ku.bazar.ui.theme.TextBlack
import com.ku.bazar.ui.theme.White

data class FavoriteItem(val imageId: Int, val title: String , val price: String)

class FavoriteItemsViewModel : ViewModel() {
    private val _favoriteItems = mutableStateListOf<FavoriteItem>()
    val favoriteItems: List<FavoriteItem> = _favoriteItems

    fun toggleFavorite(item: FavoriteItem) {
        
        
        if (_favoriteItems.contains(item)) {
            _favoriteItems.remove(item)
        } else {
            _favoriteItems.add(item)
        }
        
        
    }

    fun isFavorite(item: FavoriteItem): Boolean {
        

        return _favoriteItems.contains(item)
    }
}



@Composable
fun MyApp() {
    val navController = rememberNavController()
    val favoriteItemsViewModel: FavoriteItemsViewModel = viewModel()
    MaterialTheme {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) },
                content = { paddingValues ->

                        NavHost(
                            navController = navController,
                            startDestination = "main_screen",
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            composable("main_screen") { MainScreen(favoriteItemsViewModel) }
                            composable("favorites_screen") { FavoritesScreen(favoriteItemsViewModel) }
                        }
                }
            )

        }
    }

@Composable
fun MainScreen(favoriteItemsViewModel: FavoriteItemsViewModel,modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        BackgroundPattern(modifier = Modifier.fillMaxSize())
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TopBar()
            SearchBar()
            CategoriesSection()
            ProductSection(sectionTitle = "Recently Added", favoriteItemsViewModel)
        }
    }
}
@Composable
fun BackgroundPattern(modifier: Modifier = Modifier) {
    Row {
        Spacer(modifier = Modifier.weight(2f))
        Image(
            painter = painterResource(id = R.drawable.topographic_5
            ),
            modifier = Modifier.size(300.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            )
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

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(50.dp)
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
            .padding(bottom = 10.dp)
    ) {
        Text(
            text = "Find Desired Products Near" ,
            modifier = Modifier.padding(bottom = 4.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = TextBlack
        )
        Text(
            text = "Kathmandu University",
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextBlack
        )
        SearchBarWithButton(
            searchText = searchText,
            onSearchTextChange = { searchText = it },
            onSearchButtonClick = { /* Implement search functionality here */ }
        )
    }
}
@Composable
fun SearchBarWithButton(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search Bar
        OutlinedTextField(
            value = searchText,
            onValueChange = { onSearchTextChange(it) },
            placeholder = { Text(text = "Search") },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .height(36.dp), // Adjust the height as needed
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(20.dp)
                )
            },
            shape = RoundedCornerShape(100.dp)
        )


        IconButton(
            onClick = onSearchButtonClick,

        ) {
//            Image(
//                painter = painterResource(id = R.drawable.ic_cart),
//                contentDescription = "Category Icon",
//                modifier = Modifier
//                    .size(40.dp) // Adjust size as needed
//                    .clip(CircleShape)
//                    .background(PrimaryPink)
//                    .padding(10.dp), // Adjust padding as needed
//                contentScale = ContentScale.Crop
//            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Send,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(PrimaryPink)
                    .padding(10.dp),
                tint = White
            )
        }
    }
}


@Composable
fun CategoriesSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
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
                    Pair("Chair", R.drawable.ic_chair),
                    Pair("Books ", R.drawable.ic_book_photo),
                    Pair("Instruments", R.drawable.ic_instruments),
                    Pair("Electronics", R.drawable.ic_electronics),
                    Pair("Clothes", R.drawable.ic_clothes)
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
   /* Button(
        onClick = { /* TODO: Handle category click */ },
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .width(150.dp)
            .height(45.dp) // Adjust height as needed
            .padding(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF5F5F5), contentColor = Color.Black)
    ) {*/
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

@Composable
fun ProductSection(sectionTitle: String, favoriteItemsViewModel: FavoriteItemsViewModel) {
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = sectionTitle, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(bottom = 10.dp))
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp) // Adds equal spacing between items
        ) {
            items(10) { index ->
                val (imageId, productTitle, productPrice) = when (index) {
                    0 -> Triple(R.drawable.guitar, "Acoustic Guitar", "Rs.1800/day")
                    1 -> Triple(R.drawable.ic_tv, "HDMI2 Cable + TV", "Rs.1800/day")
                    2 -> Triple(R.drawable.ic_mobile, "Mobile", "Rs.1800/day")
                    3 -> Triple(R.drawable.ic_books, "Books", "Rs.100/day")
                    else -> Triple(R.drawable.ic_error, "Placeholder", "Rs.1800/day")
                }
                ProductItem(imageId = imageId, title = productTitle, price = productPrice, favoriteItemsViewModel = favoriteItemsViewModel)
            }
        }
    }
}

@Composable
fun ProductItem(
    imageId: Int,
    title: String,
    price: String,
    favoriteItemsViewModel: FavoriteItemsViewModel
) {
    val favoriteItem = FavoriteItem(imageId, title, price)
    val isFavorite by remember { derivedStateOf { favoriteItemsViewModel.isFavorite(favoriteItem) } }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .width(200.dp)
            .height(250.dp)
            .padding(vertical = 8.dp),
    ) {

            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "Product Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    PrimaryPink.copy(alpha = 0.1f),
                                    SecondaryPink.copy(alpha = 0.8f),
                                )
                            )
                        )
                        .zIndex(1f)
                )

            }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            contentAlignment = Alignment.TopEnd){
            IconButton(
                onClick = {
                    favoriteItemsViewModel.toggleFavorite(favoriteItem)
                },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(id = if (isFavorite) R.drawable.ic_favourite_filled else R.drawable.ic_favourite_unfill),
                    contentDescription = "Favorite Icon",
                    modifier = Modifier.size(20.dp).clip(CircleShape).background(White).padding(4.dp)
                )

            }
        }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.BottomStart

            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = title,
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.Normal, fontSize = 16.sp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = price,
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp),
                    )
                    Button(
                        onClick = { /* TODO: Handle buy now click */ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF5F5F5), contentColor = TextBlack),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(text = "Buy Now", modifier = Modifier.padding(end = 12.dp))
                        Icon(
                            imageVector = Icons.    Filled.Add,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(PrimaryPink)
                                .padding(5.dp),
                            tint = White
                        )





                    }
                }
            }
        }
    }




@Composable

fun FavoritesScreen(favoriteItemsViewModel: FavoriteItemsViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Favorite Items", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }

            LazyRow(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favoriteItemsViewModel.favoriteItems) { item ->
                    FavoriteProductItem(
                        imageId = item.imageId,
                        title = item.title,
                        price = item.price,
                        favoriteItemsViewModel = favoriteItemsViewModel,
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .fillParentMaxHeight()
                    )
                }
            }
        }
    }
}

    @Composable

    fun  FavoriteProductItem(imageId: Int, title: String, price: String, favoriteItemsViewModel: FavoriteItemsViewModel, modifier: Modifier = Modifier) {
        val favoriteItem = FavoriteItem(imageId, title, price)
        val isFavorite by remember { derivedStateOf { favoriteItemsViewModel.isFavorite(favoriteItem) } }

        Card(
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, Color.Black),
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            elevation = 8.dp
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            favoriteItemsViewModel.toggleFavorite(favoriteItem)
                        },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = if (isFavorite) R.drawable.ic_favourite_filled else R.drawable.ic_favourite_unfill),
                            contentDescription = "Favorite Icon",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(Color.White.copy(alpha = 0.7f))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = title,
                            style = TextStyle(color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
                        )
                        Text(
                            text = price,
                            style = TextStyle(color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
                        )
                        Button(
                            onClick = { /* TODO: Handle buy now click */ },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF5F5F5), contentColor = Color.Black),
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(25.dp),
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text(text = "Buy Now")
                        }
                    }
                }
            }
        }
    }


@Composable

    fun BottomNavigationBar(navController: NavHostController) {
        BottomNavigation(
            backgroundColor = Color(0xFFFFFFFF)
        ) {
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home2),
                        contentDescription = "Home",
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = { Text(text = "Home") },
                selected = navController.currentDestination?.route == "main_screen",
                onClick = {
                    navController.navigate("main_screen") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favourite2),
                        contentDescription = "Favorites",
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = { Text(text = "Favorites") },
                selected = navController.currentDestination?.route == "favorites_screen",
                onClick = {
                    navController.navigate("favorites_screen") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shoppingcart),
                        contentDescription = "Cart",
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(text = "Cart") },
                selected = navController.currentDestination?.route == "cart_screen",
                onClick = {
                    navController.navigate("cart_screen") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chat2),
                        contentDescription = "Chat",
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = { Text(text = "Chat") },
                selected = navController.currentDestination?.route == "chat_screen",
                onClick = {
                    navController.navigate("chat_screen") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }




















































