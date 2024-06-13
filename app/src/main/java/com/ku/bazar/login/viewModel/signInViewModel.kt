package com.ku.bazar.login.viewModel

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.ku.bazar.login.data.handleSignIn
import com.ku.bazar.login.data.openGoogleAccountSettings
import com.ku.bazar.login.data.signInState

class SignInViewModel : ViewModel() {

    suspend fun googleSignIn(
        context: Context,
        state: signInState,
        clientId: String,
        rememberAccount: Boolean = false,
        nonce: String? = null,
    ){
        Log.d("3","3")
        val credentialManager =  CredentialManager.create(context)

        val googleIdOption =
            GetGoogleIdOption.Builder()
                .setServerClientId(clientId)
                .setNonce(nonce)
                .setFilterByAuthorizedAccounts(rememberAccount)
                .build()

        val request =
            GetCredentialRequest.Builder()
                .setCredentialOptions(listOf(googleIdOption))
                .build()
        Log.d("4","4")
                    try {
                        Log.d("4.5","4.5")
                        val response = credentialManager.getCredential(
                            request = request,
                            context = context,
                        )
                        Log.d("5","5")
                        handleSignIn(
                            credentialResponse = response
                        )
                    } catch (e: GetCredentialException) {
                        Log.d("err","err")
                        Log.e("SignInViewModel", "GetCredentialException: ${e.javaClass.simpleName}, Message: ${e.message}")
                          handleCredentialsNotAvailable(
                                    context = context,
                                    state = state,
                                    credentialManager = credentialManager,
                                    clientId = clientId,
                                    nonce = nonce
                                )
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