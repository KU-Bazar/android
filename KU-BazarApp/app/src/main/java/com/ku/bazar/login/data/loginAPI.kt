package com.ku.bazar.login.data

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query



data class IdTokenRequest(val idToken: String)

data class AuthStatusResponse(val isAuthenticated: Boolean, val message: String)

interface ApiService {
    @POST("/auth/google") // Replace "your-endpoint" with your actual backend endpoint
    suspend fun sendIdToken(@Body request: IdTokenRequest): Response<Unit>

    @GET("/auth/google") // Replace "auth-status" with your actual endpoint to check auth status
    suspend fun checkAuthStatus(@Query("idToken") idToken: String): Response<AuthStatusResponse>
}

object RetrofitClient {
    private const val BASE_URL = "http://localhost:8080/" // Replace with your backend URL

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}