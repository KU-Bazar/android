package com.ku.bazar.mainScreen


import com.ku.bazar.productpage.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/")
    fun getProduct(@Path("id") id: Int): Call<Product>
}
