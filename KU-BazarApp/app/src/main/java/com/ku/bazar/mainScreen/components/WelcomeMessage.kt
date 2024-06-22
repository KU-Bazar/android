package com.ku.bazar.mainScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.ui.theme.TextBlack

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
                color = TextBlack,
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
            color = TextBlack,
            style = MaterialTheme.typography.body1.copy(
                fontSize = 15.sp
            )
        )
        Text(
            text = "Kathmandu University",
            color = TextBlack,
            style = MaterialTheme.typography.body1.copy(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}