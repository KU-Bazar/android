package com.ku.bazar.login.data

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Call



data class IdTokenRequest(val idToken: String)

data class AuthStatusResponse(val isAuthenticated: Boolean, val message: String)

interface ApiService {
    @POST("/users") 
     fun registerUser(@Body request: RegisterRequest): Call<User>

    @GET("/verify/") 
     fun checkAuthStatus(@Query("idToken") idToken: String): Response<AuthStatusResponse>
}

object RetrofitClient {
    private const val BASE_URL = "https://present-gopher-needed.ngrok-free.app/"

     val instance : ApiService by lazy {
         val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

         retrofit.create(ApiService::class.java)
    }
}
