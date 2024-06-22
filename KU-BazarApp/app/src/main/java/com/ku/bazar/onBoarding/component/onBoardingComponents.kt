package com.ku.bazar.onBoarding.component

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class OnBoardingViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPrefs = application.getSharedPreferences("onBoardingPrefs", Context.MODE_PRIVATE)
    private val _isFirstTime = MutableStateFlow(sharedPrefs.getBoolean("isFirstTime", true))
    val isFirstTime = _isFirstTime.asStateFlow()

    fun setFirstTimeStatus(status: Boolean) {
        _isFirstTime.value = status
        sharedPrefs.edit().putBoolean("isFirstTime", !status).apply()
    }

    @Composable
     private fun isFirstTimeLaunch(): Boolean {
         val context = LocalContext.current
         val sharedPreferences = context.getSharedPreferences("onBoardingPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("IS_FIRST_TIME", true)
    }
}
