package com.ku.bazar.mainScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ku.bazar.mainScreen.viewModel.FavoriteItemsViewModel
import com.ku.bazar.mainScreen.data.data.getDescriptionForItem


@Composable

fun FavoritesScreen(favoriteItemsViewModel: FavoriteItemsViewModel, navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PatternMain(modifier = Modifier.fillMaxSize())
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
                Text(text = "Favorite Items", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 items per row
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favoriteItemsViewModel.favoriteItems) { item ->
                    FavoriteProductItem(
                        imageId = item.imageId,
                        title = item.title,
                        price = item.price,
                        description = getDescriptionForItem(item),
                        favoriteItemsViewModel = favoriteItemsViewModel,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

    }
}