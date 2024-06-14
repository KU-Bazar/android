package com.ku.bazar.mainScreen


import com.ku.bazar.mainScreen.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/") // Adjust the endpoint as per your API design
    fun getProducts(): Call<List<Product>>
}
