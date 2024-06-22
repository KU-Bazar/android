package com.ku.bazar.mainScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.ku.bazar.R
import com.ku.bazar.mainScreen.data.data.getDescriptionForItem
import com.ku.bazar.mainScreen.viewModel.FavoriteItemsViewModel
import com.ku.bazar.mainScreen.models.Product
import com.ku.bazar.mainScreen.viewModel.FavoriteItem
import com.ku.bazar.ui.theme.PrimaryPink
import com.ku.bazar.ui.theme.SecondaryPink
import com.ku.bazar.ui.theme.TextBlack
import com.ku.bazar.ui.theme.White

@Composable
fun ProductSection(
    sectionTitle: String,
    products: List<Product>,
    favoriteItemsViewModel: FavoriteItemsViewModel
) {
    Column(modifier = Modifier.padding(vertical = 5.dp, horizontal = 24.dp)) {
        Row(
            modifier = Modifier
//                .fillMaxWidth()
                .padding(vertical = 1.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = sectionTitle, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 items per row
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
//                .padding(start = 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                ProductItem(
                    product = product,
                    favoriteItemsViewModel = favoriteItemsViewModel
                )
            }
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    favoriteItemsViewModel: FavoriteItemsViewModel
) {
    val isFavorite by remember {
        derivedStateOf {
            favoriteItemsViewModel.isFavorite(
                FavoriteItem(
                    product.Item_id,
                    product.Item_name,
                    product.Item_price.toString(), // Item_price is an Int, convert to String
                    ""
                )
            )
        }
    }

    val description = getDescriptionForItem(
        FavoriteItem(
            product.Item_id,
            product.Item_name,
            product.Item_price.toString(), // Convert Int to String
            "" // Description is fetched, so passing empty here
        )
    )

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // square aspect ratio
            .padding(8.dp), // Add padding here
    ) {
        Box(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
        ) {
            val firstImageUrl = product.imageUrls.firstOrNull() ?: ""

            Image(
                painter = rememberAsyncImagePainter(model = firstImageUrl),
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(
                    onClick = {
                        favoriteItemsViewModel.toggleFavorite(
                            FavoriteItem(
                                product.Item_id,
                                product.Item_name,
                                product.Item_price.toString(), // Convert Int to String
                                ""
                            )
                        )
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
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = product.Item_name,
                    style = TextStyle(color = Color.White, fontWeight = FontWeight.Normal, fontSize = 16.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Rs.${product.Item_price}", // Assuming Item_price is in dollars
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
