package com.ku.bazar.login.data

import android.util.Log
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

fun handleSignIn(result: GetCredentialResponse, coroutineScope: CoroutineScope) {

    when (val credential = result.credential) {
        is CustomCredential -> {
            if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                try {
                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(credential.data)
                    val idToken = googleIdTokenCredential.idToken

                    coroutineScope.launch {
                        try {
                            val response = RetrofitClient.apiService.sendIdToken(IdTokenRequest(idToken))
                            if (response.isSuccessful) {
                                Log.d("MainActivity", "ID Token sent successfully")
                                // Check authentication status
                                checkAuthStatus(idToken, coroutineScope)
                            } else {
                                Log.e("MainActivity", "Failed to send ID Token: ${response.errorBody()?.string()}")
                            }
                        } catch (e: Exception) {
                            Log.e("MainActivity", "Error sending ID Token", e)
                        }
                    }
                } catch (e: GoogleIdTokenParsingException) {
                    Log.e("MainActivity", "handleSignIn:", e)
                }
            } else {
                Log.e("MainActivity", "Unexpected type of credential")
            }
        }

        else -> {
            Log.e("MainActivity", "Unexpected type of credential")
        }
    }
}


fun checkAuthStatus(idToken: String, coroutineScope: CoroutineScope) {
    coroutineScope.launch {
        try {
            val response = RetrofitClient.apiService.checkAuthStatus(idToken)
            if (response.isSuccessful) {
                val authStatus = response.body()
                if (authStatus?.isAuthenticated == true) {
                    Log.d("MainActivity", "User is authenticated: ${authStatus.message}")
                } else {
                    Log.e("MainActivity", "User is not authenticated: ${authStatus?.message}")
                }
            } else {
                Log.e("MainActivity", "Failed to check auth status: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error checking auth status", e)
        }
    }
}

/*import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.identity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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
    @POST("your-endpoint") // Replace "your-endpoint" with your actual backend endpoint
    suspend fun sendIdToken(@Body request: IdTokenRequest): Response<Unit>

    @GET("auth-status") // Replace "auth-status" with your actual endpoint to check auth status
    suspend fun checkAuthStatus(@Query("idToken") idToken: String): Response<AuthStatusResponse>
}

object RetrofitClient {
    private const val BASE_URL = "https://your-backend-url.com" // Replace with your backend URL

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

@Composable
fun GoogleLoginButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Login with Google")
    }
}

fun checkAuthStatus(idToken: String, coroutineScope: CoroutineScope) {
    coroutineScope.launch {
        try {
            val response = RetrofitClient.apiService.checkAuthStatus(idToken)
            if (response.isSuccessful) {
                val authStatus = response.body()
                if (authStatus?.isAuthenticated == true) {
                    Log.d("MainActivity", "User is authenticated: ${authStatus.message}")
                } else {
                    Log.e("MainActivity", "User is not authenticated: ${authStatus?.message}")
                }
            } else {
                Log.e("MainActivity", "Failed to check auth status: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error checking auth status", e)
        }
    }
}

fun handleSignIn(result: GetCredentialResponse, coroutineScope: CoroutineScope) {
    when (val credential = result.credential) {
        is CustomCredential -> {
            if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                try {
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    val idToken = googleIdTokenCredential.idToken

                    // Launch coroutine to send the ID token to your backend
                    coroutineScope.launch {
                        try {
                            val response = RetrofitClient.apiService.sendIdToken(IdTokenRequest(idToken))
                            if (response.isSuccessful) {
                                Log.d("MainActivity", "ID Token sent successfully")
                                // Check authentication status
                                checkAuthStatus(idToken, coroutineScope)
                            } else {
                                Log.e("MainActivity", "Failed to send ID Token: ${response.errorBody()?.string()}")
                            }
                        } catch (e: Exception) {
                            Log.e("MainActivity", "Error sending ID Token", e)
                        }
                    }
                } catch (e: GoogleIdTokenParsingException) {
                    Log.e("MainActivity", "handleSignIn:", e)
                }
            } else {
                Log.e("MainActivity", "Unexpected type of credential")
            }
        }
        else -> {
            Log.e("MainActivity", "Unexpected type of credential")
        }
    }
}

@Composable
fun MyApp() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    GoogleLoginButton(onClick = {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(SERVER_CLIENT_ID)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val credentialManager = Identity.getCredentialManager(context)

        coroutineScope.launch {
            try {
                val result = credentialManager.getCredential(context, request)
                handleSignIn(result, coroutineScope)
            } catch (e: GetCredentialException) {
                Log.e("MainActivity", "GetCredentialException", e)
            }
        }
    })
}
*/