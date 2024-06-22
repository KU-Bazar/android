package com.ku.bazar.onBoarding.model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ku.bazar.onBoarding.component.OnBoardingViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnBoardingViewModelFactory(
    private val application: Application) :
        ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnBoardingViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return OnBoardingViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

