package com.ku.bazar.mainScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
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
import com.ku.bazar.R
import com.ku.bazar.mainScreen.viewModel.FavoriteItem
import com.ku.bazar.ui.theme.PrimaryPink
import com.ku.bazar.ui.theme.SecondaryPink
import com.ku.bazar.ui.theme.White

@Composable
fun FavoriteProductItem(
    imageId: Int,
    title: String,
    price: String,
    description: String,
    favoriteItemsViewModel: com.ku.bazar.mainScreen.viewModel.FavoriteItemsViewModel,
    modifier: Modifier = Modifier,
) {
    val favoriteItem = FavoriteItem(imageId, title, price, description)
    val isFavorite by remember { derivedStateOf { favoriteItemsViewModel.isFavorite(favoriteItem) } }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = price,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = description,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        ),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(
                    onClick = {
                        favoriteItemsViewModel.toggleFavorite(favoriteItem)
                    },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        painter = painterResource(id = if (isFavorite) R.drawable.ic_favourite_filled else R.drawable.ic_favourite_unfill),
                        contentDescription = "Favorite Icon",
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(White)
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}