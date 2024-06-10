package com.ku.bazar.login.data

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import android.provider.Settings
import android.util.Log


@Stable
class signInState(open: Boolean = false) {
    var opened by mutableStateOf(open)
        private set

    fun open() {
        opened = true
    }

    internal fun close() {
        opened = false
    }
}

fun openGoogleAccountSettings(context: Context) {
    try {
        val addAccountIntent = Intent(Settings.ACTION_ADD_ACCOUNT).apply {
            putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.google"))
        }
        context.startActivity(addAccountIntent)
    } catch (e: Exception) {
        Log.e(TAG, "openGoogleAccountSettings Error: $e")
    }
}