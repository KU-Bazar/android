package com.ku.bazar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ku.bazar.util.OnBoarding

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun firstScreen(){
    Column(
//        modifier = Modifier
//            .fillMaxHeight()
//            .fillMaxWidth()
    )
    {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(height = 400.dp, width = 400.dp),
            painter = painterResource(id = OnBoarding.First.image),
            contentDescription = "@string/con_description"
        )
    }
}