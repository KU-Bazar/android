package com.ku.bazar.productListing.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.ku.bazar.productListing.components.ProductItem
import com.ku.bazar.productListing.components.ProductListingBar
import com.ku.bazar.productListing.components.productError
import com.ku.bazar.productListing.components.productLoading
import com.ku.bazar.productpage.models.Product
import com.ku.bazar.productpage.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun mainProdcutListingScreen(heading: String, subheading: String) {
    var products by remember { mutableStateOf(listOf<Product>()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(heading) {
        if (subheading == "Category") {
            getCategoryData(heading) { fetchedProducts ->
                products = fetchedProducts
                isLoading = false
            }
        } else {
            getSearchedData(heading) { fetchedProducts ->
                products = fetchedProducts
                isLoading = false
            }
        }
    }
    Scaffold(
        topBar = {
            ProductListingBar(heading = heading, subheading)
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when {
                    isLoading -> {
                        productLoading()
                    }
                    products.isEmpty() -> {
                        productError()
                    }
                    else -> {
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
                val products = response.body() ?: emptyList()
                onCategoryDataFetch(products)
            } else {
                Log.e("CATEGORY", "Failed to fetch category data: ${response.message()}")
                onCategoryDataFetch(emptyList())
            }
        }
        override fun onFailure(call: Call<List<Product>>, t: Throwable) {
            Log.e("CATEGORY", "Failed to fetch category data: ${t.message}")
            onCategoryDataFetch(emptyList())
        }
    })
}

private fun getSearchedData(search: String, onSearchFetched: (List<Product>) -> Unit) {
    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiService::class.java)
    val retrofitData = retrofitBuilder.getSearchedItems(search)
    retrofitData.enqueue(object : Callback<List<Product>> {
        override fun onResponse(
            call: Call<List<Product>>,
            response: Response<List<Product>>
        ) {
            if (response.isSuccessful) {
                val products = response.body() ?: emptyList()
                onSearchFetched(products)
            } else {
                Log.e("SEARCH", "Failed to fetch search data: ${response.message()}")
                onSearchFetched(emptyList())
            }
        }
        override fun onFailure(call: Call<List<Product>>, t: Throwable) {
            Log.e("SEARCH", "Failed to fetch search data: ${t.message}")
            onSearchFetched(emptyList())
        }
    })
}
