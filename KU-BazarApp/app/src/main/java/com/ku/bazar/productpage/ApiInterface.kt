package com.ku.bazar.productpage


import com.ku.bazar.productpage.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/product/{id}")
    fun getProduct(@Path("id") id: Int): Call<Product>
}
