package com.ku.bazar.login.viewModel

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.ku.bazar.login.data.handleSignIn
import com.ku.bazar.login.data.openGoogleAccountSettings
import com.ku.bazar.login.data.signInState
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    @Composable
    fun GoogleSignIn(
        state: signInState,
        clientId: String,
        rememberAccount: Boolean = true,
        nonce: String? = null,
    ){
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
                Log.e(ContentValues.TAG, errorMessage)
                state.close()
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "${e.message}")
                state.close()
            }
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "${e.message}")
            state.close()
        }
    }
}