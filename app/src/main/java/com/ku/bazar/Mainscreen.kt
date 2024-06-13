package com.ku.bazar

import androidx.compose.animation.core.tween


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
import androidx.compose.foundation.clickable

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable
import com.ku.bazar.ui.theme.PrimaryPink
import com.ku.bazar.ui.theme.SecondaryPink
import com.ku.bazar.ui.theme.TextBlack
import com.ku.bazar.ui.theme.White



data class Product(
    val imageId: Int,
    val title: String,
    val price: String,
    val description: String
)


data class FavoriteItem(val imageId: Int, val title: String , val price: String , val description: String)

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

    fun getDescriptionForItem(item: FavoriteItem): String {
        // Assuming you have a map of descriptions where the key is the title of the item
        val descriptions = mapOf(
            "Acoustic Guitar" to "This is a good guitar for beginners to kickstart their career in music  ",
            "HDMI2 Cable + TV" to "This cable supports high-definition video and audio.",
            "Mobile" to "Latest smartphone with great camera and performance.",
            "Books" to "Best-selling novels for book lovers.",
            // Add more descriptions as needed
        )

        // Retrieve the description based on the title of
        return descriptions[item.title] ?: "Description not available" // Default description if not found
    }


}



@Composable

fun MyApp() {
    val navController = rememberNavController()
    val favoriteItemsViewModel: FavoriteItemsViewModel = viewModel()

    MaterialTheme {
        Scaffold(

            content = { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    NavHost(
                        navController = navController,
                        startDestination = NavigationBarItems.Home.route
                    ) {
                        composable(NavigationBarItems.Home.route) {
                            MainScreen(favoriteItemsViewModel, navController)
                        }
                        composable(NavigationBarItems.Favorite.route) {
                            FavoritesScreen(favoriteItemsViewModel,navController)
                        }

                    }
                }
            }
        )
    }
}


@Composable
fun MainScreen(
    favoriteItemsViewModel: FavoriteItemsViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
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

            NavBar(navController = navController)
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
                .weight(0.8f)
                .padding(end = 8.dp)
                .height(50.dp), // Adjust the height as needed
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(20.dp)
                )
            },

            shape = RoundedCornerShape(100.dp),
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                placeholderColor = Color.Gray,
                textColor = Color.Black,
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedBorderColor = PrimaryPink,
                unfocusedBorderColor = Color(0xFFF5F5F5)
            ),




        )


        IconButton(
            onClick = onSearchButtonClick,

        ) {

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
                val Product = when (index) {
                    0 -> Product(R.drawable.guitar, "Acoustic Guitar", "Rs.1800/day","This is a Good guitar")
                    1 -> Product(R.drawable.ic_tv, "HDMI2 Cable + TV", "Rs.1800/day","This is a Good Tv")
                    2 -> Product(R.drawable.ic_mobile, "Mobile", "Rs.1800/day","This is a Good Mobile")
                    3 -> Product(R.drawable.ic_books, "Books", "Rs.100/day","This is a Good book")
                    else -> Product(R.drawable.ic_error, "Placeholder", "Rs.1800/day","This is a Good error")
                }
                ProductItem(imageId =Product.imageId, title =Product.title, price = Product.price, description = Product.description,favoriteItemsViewModel = favoriteItemsViewModel)


            }
        }
    }
}

@Composable
fun ProductItem(
    imageId: Int,
    title: String,
    price: String,
    description: String,
    favoriteItemsViewModel: FavoriteItemsViewModel
) {
    val favoriteItem = FavoriteItem(imageId, title, price,description)
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
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(White)
                        .padding(4.dp)
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
fun PopularItem(
    imageId: Int,
    title: String,
    price: String,
    description: String,
    favoriteItemsViewModel: FavoriteItemsViewModel,
    modifier: Modifier = Modifier
) {
    val favoriteItem = FavoriteItem(imageId, title, price,description )
    val isFavorite by remember { derivedStateOf { favoriteItemsViewModel.isFavorite(favoriteItem) } }

    if (isFavorite) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .width(200.dp)
                .height(250.dp)
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
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
                                imageVector = Icons.Filled.Add,
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
    }
}





@Composable

fun FavoritesScreen(favoriteItemsViewModel: FavoriteItemsViewModel ,navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BackgroundPattern(modifier = Modifier.fillMaxSize())
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
                Text(text = "Favorite Items", fontWeight = FontWeight.SemiBold, fontSize = 20.sp, )
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
                        description = favoriteItemsViewModel.getDescriptionForItem(item),


                        favoriteItemsViewModel = favoriteItemsViewModel,
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .fillParentMaxHeight()
                    )
                }
            }
        }
        NavBar(navController = navController)
    }
}

    @Composable
    fun FavoriteProductItem(
        imageId: Int,
        title: String,
        price: String,
        description: String,
        favoriteItemsViewModel: FavoriteItemsViewModel,
        modifier: Modifier = Modifier,

        ) {

        val favoriteItem = FavoriteItem(imageId, title, price, description)
        val isFavorite by remember { derivedStateOf { favoriteItemsViewModel.isFavorite(favoriteItem) } }


        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth() // Take the whole width of the screen
                .padding(top = 20.dp, bottom = 100.dp)

            // Padding for top and bottom
        ) {
            Box(
                modifier = Modifier

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


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.TopEnd
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
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(White)
                            .padding(4.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.BottomStart

            ) {
                Column(horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,

                        )

                    Text(
                        text = description,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        ),
                        maxLines = 3, // Adjust as needed
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        text = price,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                    )
                    Button(
                        onClick = { /* TODO: Handle buy now click */ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFF5F5F5),
                            contentColor = TextBlack
                        ),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(text = "Buy Now", modifier = Modifier.padding(end = 12.dp))
                        Icon(
                            imageVector = Icons.Filled.Add,
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
fun NavBar(navController: NavController) {
    val navigationBarItems = remember { NavigationBarItems.values() }
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter

    ) {

        AnimatedNavigationBar(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(),
            selectedIndex = selectedIndex,
            cornerRadius = shapeCornerRadius(cornerRadius = 34.dp),
            ballAnimation = Parabolic(tween(300)),
            indentAnimation = Height(tween(300)),
            barColor = Color(0xFFF5F5F5),
            ballColor = PrimaryPink
        ) {
            navigationBarItems.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .noRippleClickable {
                            selectedIndex = index
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(26.dp),
                        imageVector = item.icon,
                        contentDescription = "Bottom Bar Icon",
                        tint = if (selectedIndex == index) PrimaryPink else TextBlack
                    )

                }
            }

        }
    }
}

enum class NavigationBarItems(val icon: ImageVector, val route: String) {
    Home(icon = Icons.Default.Home, route = "main_screen"),
    Favorite(icon = Icons.Default.Favorite, route = "favorites_screen"),
    Cart(icon = Icons.Default.ShoppingCart, route = "cart_screen"),
    Chat(icon = Icons.Default.Email, route = "chat_screen"),
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.then(
        clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            onClick()
        }
    )
}




