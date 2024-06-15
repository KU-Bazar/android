package com.ku.bazar.productListing.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import coil.compose.AsyncImage
import com.ku.bazar.R
import com.ku.bazar.mainScreen.FavoriteItem
import com.ku.bazar.mainScreen.FavoriteItemsViewModel
import com.ku.bazar.productpage.models.Product
import com.ku.bazar.ui.theme.PrimaryPink
import com.ku.bazar.ui.theme.SecondaryPink
import com.ku.bazar.ui.theme.TextBlack
import com.ku.bazar.ui.theme.White
import org.jetbrains.annotations.Async

@Composable
fun ProductItem(
    product:Product,
//    favoriteItemsViewModel: FavoriteItemsViewModel
) {
//    val favoriteItem = FavoriteItem(imageId, title, price,description)
//    val isFavorite by remember { derivedStateOf { favoriteItemsViewModel.isFavorite(favoriteItem) } }

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
            AsyncImage(model = product.Image_url?.first(), contentDescription="Product Image", modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop)
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
//        Box(modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//            contentAlignment = Alignment.TopEnd){
//            IconButton(
//                onClick = {
////                    favoriteItemsViewModel.toggleFavorite(favoriteItem)
//                },
//                modifier = Modifier.align(Alignment.TopEnd)
//            ) {
//                Icon(
//                    painter = painterResource(id = if (isFavorite) R.drawable.ic_favourite_filled else R.drawable.ic_favourite_unfill),
//                    contentDescription = "Favorite Icon",
//                    modifier = Modifier
//                        .size(20.dp)
//                        .clip(CircleShape)
//                        .background(White)
//                        .padding(4.dp)
//                )
//
//            }
//        }

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
                    text = product.Item_price.toString(),
                    style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp),
                )
                Button(
                    onClick = { /* TODO: Handle buy now click */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF5F5F5), contentColor = TextBlack),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = "Chat Now", modifier = Modifier.padding(end = 12.dp))
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