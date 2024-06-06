package com.ku.bazar.chat.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ku.bazar.chat.components.mainAppBar

@Composable
fun mainScreen(){
    Scaffold (
        topBar = {
            mainAppBar()
        },
        containerColor = Color.Transparent,
            ){paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ){
            //yo bhitra conversaton Box haru
        }
    }
}