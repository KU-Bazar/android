package com.ku.bazar.mainScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ku.bazar.R

@Composable
fun PatternMain(modifier: Modifier = Modifier) {
    Row {
        Spacer(modifier = Modifier.weight(2f))
        Image(
            painter = painterResource(id = R.drawable.topographic_5
            ),
            modifier = Modifier.size(300.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }

}