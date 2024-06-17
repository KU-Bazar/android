package com.ku.bazar.mainScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Welcome(userName: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hi, $userName",
                style = MaterialTheme.typography.h5.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "👋",
                style = MaterialTheme.typography.h5.copy(
                    fontSize = 24.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Find Desired Products Near",
            style = MaterialTheme.typography.body1.copy(
                fontSize = 18.sp
            )
        )
        Text(
            text = "Kathmandu University",
            style = MaterialTheme.typography.body1.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}