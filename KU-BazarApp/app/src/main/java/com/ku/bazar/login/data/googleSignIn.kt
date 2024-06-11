package com.ku.bazar.login.data

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
//import android.credentials.GetCredentialRequest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
//import androidx.credentials.CredentialManager
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import kotlinx.coroutines.launch
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.*





//@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun googleSignIn(
    state: signInState,
    clientId: String,
    rememberAccount: Boolean = true,
    nonce: String? = null,
    ) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val credentialManager = remember { CredentialManager.create(context) }

    val googleIdOption = remember {
        GetGoogleIdOption.Builder()
            .setServerClientId(clientId)
            .setNonce(nonce)
            .setFilterByAuthorizedAccounts(rememberAccount)
            .build()
    }

    val request = remember {
        GetCredentialRequest.Builder()
            .setCredentialOptions(listOf(googleIdOption))
            .build()
    }

    LaunchedEffect(key1 = state.opened) {
        if (state.opened) {
            scope.launch {
                try {
                    val response = credentialManager.getCredential(
                        request = request,
                        context = context,
                    )
                    handleSignIn(
                        credentialResponse = response
                    )
                } catch (e: GetCredentialException) {
                    if (e.message != null) {
                        if (e.message!!.contains("No credentials available")) {
                            handleCredentialsNotAvailable(
                                context = context,
                                state = state,
                                credentialManager = credentialManager,
                                clientId = clientId,
                                nonce = nonce
                            )
                        }


                    }
                }
            }
        }
    }
}


private suspend fun handleCredentialsNotAvailable(
    context: Context,
    state: signInState,
    credentialManager: CredentialManager,
    clientId: String,
    nonce: String?
) {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setServerClientId(clientId)
                .setNonce(nonce)
                .setFilterByAuthorizedAccounts(false)
                .build()

            val request = GetCredentialRequest.Builder()
                .setCredentialOptions(listOf(googleIdOption))
                .build()

            try {
                val response = credentialManager.getCredential(
                    request = request,
                    context = context,
                )
                handleSignIn(
                    credentialResponse = response
                )
            } catch (e: GetCredentialException) {
                try {
                    if (e.message!!.contains("No credentials available")) {
                        openGoogleAccountSettings(context = context)
                    }
                    val errorMessage = if (e.message != null) {
                        if (e.message!!.contains("activity is cancelled by the user.")) {
                            "Dialog Closed."
                        } else if (e.message!!.contains("Caller has been temporarily blocked")) {
                            "Sign in has been Temporarily Blocked due to too many Closed Prompts."
                        } else {
                            e.message.toString()
                        }
                    } else "Unknown Error."
                    Log.e(TAG, errorMessage)
                    state.close()
                } catch (e: Exception) {
                    Log.e(TAG, "${e.message}")
                    state.close()
                }
            } catch (e: Exception) {
                Log.e(TAG, "${e.message}")
                state.close()
            }
}

