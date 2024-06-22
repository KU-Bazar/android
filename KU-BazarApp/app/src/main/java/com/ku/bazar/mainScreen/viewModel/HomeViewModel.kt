package com.ku.bazar.mainScreen.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ku.bazar.mainScreen.ApiService
import com.ku.bazar.mainScreen.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.awaitResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
class HomeViewModel : ViewModel() {

    val searchQuery = mutableStateOf("")
    val products = mutableStateOf<List<Product>>(emptyList())
    val errorMessage = mutableStateOf("")

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://fine-moral-seasnail.ngrok-free.app")
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    init {
        fetchProducts()
    }

    fun updateSearchInputValue(value: String) {
        this.searchQuery.value = value
    }
    private fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getProducts().awaitResponse()
                if (response.isSuccessful) {
                    response.body()?.let {
                        products.value = it
                    } ?: run {
                        errorMessage.value = "No products found"
                    }
                } else {
                    errorMessage.value = "Response not successful: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Failed to fetch products: ${e.message}"
            }
        }
    }
}