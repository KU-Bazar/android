package com.ku.bazar.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ku.bazar.mainScreen.components.*
import com.ku.bazar.mainScreen.data.MainScreen
import com.ku.bazar.mainScreen.models.Product
import com.ku.bazar.mainScreen.viewModel.FavoriteItemsViewModel
import com.ku.bazar.mainScreen.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    favoriteItemsViewModel: FavoriteItemsViewModel,
    userName: String
) {
    val searchQuery by remember { homeViewModel.searchQuery }
    val productsListState by homeViewModel.products

    val currentRoute = remember { mutableStateOf(MainScreen.Home.route) }
    val cartOffset = remember { mutableStateOf(IntOffset.Zero) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(2)
            ) {
                item(
                    span = { GridItemSpan(2) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Profile()
                    }
                }

                item(
                    span = { GridItemSpan(2) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Welcome(userName)
                    }
                }

                item(
                    span = { GridItemSpan(2) }
                ) {
                    SearchBar(
                        value = searchQuery,
                        onValueChange = { homeViewModel.updateSearchInputValue(it) },
                        onFocusChange = {},
                        onImeActionClicked = { /* We should run the search now */ }
                    )
                }

                item(
                    span = { GridItemSpan(2) }
                ) {
                    CategoriesSection()
                }

                item(
                    span = { GridItemSpan(2) }
                ) {
                    ProductSection(
                        sectionTitle = "Recently Added",
                        products = productsListState,
                        favoriteItemsViewModel = favoriteItemsViewModel
                    )
                }

                items(productsListState.size) { index ->
                    val product = productsListState[index]
                    ProductItem(
                        product = product,
                        favoriteItemsViewModel = favoriteItemsViewModel
                    )
                }

                item(span = { GridItemSpan(2) }) {
                    Spacer(modifier = Modifier.height(56.dp))
                }
            }
        }

        AppBottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            activeRoute = currentRoute.value,
            bottomNavDestinations = listOf(MainScreen.Home, MainScreen.Favorite, MainScreen.Chat, MainScreen.Profile),
            backgroundColor = Color.Black,
            onCartOffsetMeasured = { cartOffset.value = it },
            onActiveRouteChange = { newRoute -> currentRoute.value = newRoute },
            navHostController
        )
    }
}
