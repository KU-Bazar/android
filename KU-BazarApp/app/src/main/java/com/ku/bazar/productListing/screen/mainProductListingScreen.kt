package com.ku.bazar.productListing.screen
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ku.bazar.productpage.ApiService
import com.ku.bazar.chat.models.Conversation
import com.ku.bazar.productListing.components.ProductItem
import com.ku.bazar.productListing.components.ProductListingBar
import com.ku.bazar.productListing.models.Category
import com.ku.bazar.productpage.models.Product
import com.ku.bazar.productListing.models.getCategoryDisplayName
import com.ku.bazar.productpage.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable

fun mainProdcutListingScreen(category: Category){
    val categoryName = getCategoryDisplayName(category)
    var products by remember { mutableStateOf(listOf<Product>()) }
    LaunchedEffect(categoryName) {
        getCategoryData(categoryName) { fetchedProducts ->
            products = fetchedProducts
        }
    }

    Scaffold(
        topBar = {
            ProductListingBar(heading = categoryName, subheading = "Category")
        },
        content = { innerPadding ->

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),

                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(products.size) { index ->
                        val product = products[index]
                        ProductItem(product = product)
                    }
                }
        }
    )
}

private fun getCategoryData(category: String, onCategoryDataFetch: (List<Product>) -> Unit) {
    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiService::class.java)
    val retrofitData = retrofitBuilder.getCategoryItems(category)
    retrofitData.enqueue(object : Callback<List<Product>> {
        override fun onResponse(
            call: Call<List<Product>>,
            response: Response<List<Product>>
        ) {
            if (response.isSuccessful) {
                val conversations = response.body() ?: emptyList()
                onCategoryDataFetch(conversations)
            } else {
                Log.e("CATEGORORY", "Failed to fetch conversation data: ${response.message()}")
            }
        }
        override fun onFailure(call: Call<List<Product>>, t: Throwable) {
            Log.e("CATEGORORY", "Failed to fetch category data: ${t.message}")
        }
    })
}