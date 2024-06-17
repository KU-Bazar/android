package com.ku.bazar.mainScreen.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    val searchQuery = mutableStateOf("")

    fun updateSearchInputValue(value: String) {
        this.searchQuery.value = value
    }
}