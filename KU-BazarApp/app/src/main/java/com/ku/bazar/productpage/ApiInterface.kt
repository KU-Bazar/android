package com.ku.bazar.productpage


import com.ku.bazar.productpage.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://fine-moral-seasnail.ngrok-free.app"
interface ApiService {
    @GET("/product/{id}")
    fun getProduct(@Path("id") id: Int): Call<Product>
    @GET("/category/{name}")
    fun getCategoryItems(@Path("name") name:String) : Call<List<Product>>
    @GET("/search/product/{query}")
    fun getSearchedItems(@Path("query") query:String) : Call<List<Product>>

}

