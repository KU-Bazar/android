package com.ku.bazar.Onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
//@Preview(showBackground = true, showSystemUi = true)
fun topSection(
    showBackButton: Boolean = false,
    onBackClicked: () -> Unit
){
    Box(contentAlignment = Alignment.TopStart,
    modifier = Modifier.size(50.dp,50.dp)){
        if (showBackButton) {
            IconButton(onClick = { onBackClicked }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    contentDescription = "@string/wall_des"
                )
            }
        }
    }
}