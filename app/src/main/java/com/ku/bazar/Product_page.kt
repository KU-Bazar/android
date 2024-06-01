package com.ku.bazar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.ui.theme.KUBazarTheme
import com.ku.bazar.ui.theme.Dark100
import com.ku.bazar.ui.theme.Dark500
import com.ku.bazar.ui.theme.Pink500

class Productpage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KUBazarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProductDetailScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductDetailScreen(modifier: Modifier = Modifier) {
    // Hardcoded product details
    val productName = "Sample Plant"
    val productDescription = "This is a detailed description of the sample plant. It has many features and benefits that you will love."
    val productPrice = "Rs.750"
    val sellerName = "Bipul Lama"
    val productImageRes = R.drawable.plant // Update this line to reference plant.jpg

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = productImageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Reduced the height for better clarity
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = productName,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Dark500,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = productDescription,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Dark100,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Price: $productPrice",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Pink500,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Seller: $sellerName",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Dark500,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ProductDetailScreenPreview() {
    KUBazarTheme {
        ProductDetailScreen()
    }
}
