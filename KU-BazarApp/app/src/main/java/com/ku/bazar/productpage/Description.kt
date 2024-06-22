package com.ku.bazar.productpage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ku.bazar.R

import com.ku.bazar.ui.theme.PrimaryPink
import com.ku.bazar.ui.theme.White
import com.ku.bazar.productpage.models.Product
import com.ku.bazar.ui.theme.TextBlack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale


@Composable
fun Description(navController: NavHostController, productId: Int) {
    val productState = remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(Unit) {
        getProductDetails(productId) { result ->
            productState.value = result
        }
    }

    val images = productState.value?.Image_url ?: emptyList()


    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 45.dp,
                        bottomEnd = 45.dp
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()


                .offset(y = 90.dp, x = 120.dp)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.topographic_6),
                contentDescription = "Pattern",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()


            )


        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                //.clickable { }
                .align(Alignment.TopStart)
                .padding(start = 30.dp, top = 40.dp)
        )

        {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { }
                    .background(color = Color(0xFFF5F5F5))
                    .padding(10.dp),
                tint = Color.Black
            )
        }


        Box(
            modifier = Modifier
                .width(310.dp)
                .align(Alignment.CenterEnd)
                .height(330.dp)
                .offset(y = (-120).dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 100.dp
                    )
                )


        )
        {
            LazyRow {
                items(images) { imageRes ->
                    Box(
                        modifier = Modifier
                            .width(310.dp)
                            .height(330.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = imageRes),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            PrimaryPink.copy(alpha = 0f),
                                            PrimaryPink.copy(alpha = 0f),
                                            PrimaryPink.copy(alpha = 0f),
                                            PrimaryPink.copy(alpha = 0f),
                                            White.copy(alpha = 1f),
                                        )
                                    )
                                )
                        )
                    }
                }
            }
        }




        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .offset(y = 100.dp, x = 340.dp)


        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_favourite_unfill),
                contentDescription = "Favorite Icon",
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .clickable { }
                    .background(color = Color(0xFFF5F5F5))
                    .padding(8.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .offset(y = 100.dp, x = 10.dp)
        )
        {
            productState.value?.let {
                Text(
                    text = "Buy " + it.Item_name,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 50.dp)
                )
            }
            productState.value?.let {
                Text(
                    text = "Rs. " + it.Item_price,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 80.dp)
                )
            }

            Row(modifier = Modifier.padding(top = 110.dp)) {


                Button(
                    onClick = { /* TODO: Handle buy now click */ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryPink,
                        contentColor = Color.White

                    ),
                    shape = RoundedCornerShape(20.dp),
                    //  modifier = Modifier.padding(top = 110.dp)
                ) {
                    Text(text = "Chat  Now", modifier = Modifier.padding(end = 12.dp))
                    Icon(
                        imageVector = Icons.Filled.MailOutline,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(color = Color(0xFFF5F5F5))
                            .padding(5.dp),
                        tint = PrimaryPink
                    )

                }

                // Category Icon and Text
                val categoryIcons = mapOf(
                    "Furniture" to R.drawable.ic_furniture,
                    "Clothing" to R.drawable.ic_clothing,
                    "Electronics" to R.drawable.ic_electronics,
                    "Books" to R.drawable.ic_books,
                    "Other" to R.drawable.ic_other
                )
                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .height(40.dp)
                        .padding(start = 5.dp, top = 5.dp)
                        .background(
                            color = Color(0xFFF5F5F5),
                            shape = RoundedCornerShape(20.dp)
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(4.dp)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        productState.value?.let { product ->
                            val category = product.category.capitalize(Locale.ROOT)
                            val iconResId = categoryIcons[category]
                            iconResId?.let { resourceId ->
                                Image(
                                    painter = painterResource(id = resourceId),
                                    contentDescription = "Category Icon",
                                    modifier = Modifier
                                        .size(26.dp)
                                        .clip(CircleShape)
                                        .background(PrimaryPink)
                                        .padding(4.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = category,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = TextBlack
                            )
                        }
                    }
                }
            }
        }
        // Product Description and Seller
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .offset(y = 240.dp, x = 10.dp)
        ) {
            productState.value?.let { product ->
                Text(
                    text = product.Item_desc,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 30.dp)
                )
                Text(
                    text = "Seller: ${product.Item_seller}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }

}
private fun getProductDetails(productId: Int, onResult: (Product?) -> Unit
) {
    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiService::class.java)

    val retrofitData = retrofitBuilder.getProduct(productId)

    retrofitData.enqueue(object : Callback<com.ku.bazar.productpage.models.Product> {
        override fun onResponse(call: Call<com.ku.bazar.productpage.models.Product>, response: Response<com.ku.bazar.productpage.models.Product>) {
            if (response.isSuccessful) {
                onResult(response.body())
            } else {
                onResult(null)
            }
        }


        override fun onFailure(call: Call<com.ku.bazar.productpage.models.Product>, t: Throwable) {
            Log.d("FAILED", t.toString())
            onResult(null)
        }
    })
}








