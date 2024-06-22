package com.ku.bazar.login.data

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import androidx.compose.runtime.saveable.Saver

fun handleSignIn(
    credentialResponse: GetCredentialResponse
) {
    Log.d("6","6")
    when (val credential = credentialResponse.credential) {
        is CustomCredential -> {
            if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                try {
                    Log.d("7","7")
                    val googleIdTokenCredential = GoogleIdTokenCredential
                        .createFrom(credential.data)
                } catch (e: GoogleIdTokenParsingException) {
                    Log.d("Invalid Google tokenId response:" ,"invalid brother")
                }
            } else {
                Log.d("Unexpected Type of Credential.","La myaa k bhayo")
            }
        }

        else -> {
            Log.d("Unexpected Type of Credential.","La myaa pheri k bhayo")
        }
    }
}


//
//fun checkAuthStatus(idToken: String, coroutineScope: CoroutineScope) {
//    coroutineScope.launch {
//        try {
//            val response = RetrofitClient.apiService.checkAuthStatus(idToken)
//            if (response.isSuccessful) {
//                val authStatus = response.body()
//                if (authStatus?.isAuthenticated == true) {
//                    Log.d("MainActivity", "User is authenticated: ${authStatus.message}")
//                } else {
//                    Log.e("MainActivity", "User is not authenticated: ${authStatus?.message}")
//                }
//            } else {
//                Log.e("MainActivity", "Failed to check auth status: ${response.errorBody()?.string()}")
//            }
//        } catch (e: Exception) {
//            Log.e("MainActivity", "Error checking auth status", e)
//        }
//    }
//}

@Composable
fun rememberOneTapSignInState(): signInState {
    return rememberSaveable(
        saver = OneTapSignInStateSaver
    ) { signInState() }
}

private val OneTapSignInStateSaver: Saver<signInState, Boolean> = Saver(
    save = { state -> state.opened },
    restore = { opened -> signInState(open = opened) },
)
