package com.ku.bazar

import android.content.Intent // Added line
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext // Added line
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp // Added line
import com.ku.bazar.ui.theme.KUBazarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setDecorFitsSystemWindows(true)
        setContent {
            KUBazarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen() // Changed from ProductDetailScreen() to MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Ensures padding is added
    ) {
        // Existing content of the screen can be added here

        // Button added at the bottom corner
        val context = LocalContext.current
        Button(
            onClick = {
                val intent = Intent(context, Productpage::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd) // Positioning the button at the bottom corner
                .padding(16.dp) // Ensures padding around the button
        ) {
            Text(text = "Open Product Page")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    KUBazarTheme {
        Greeting("Android")
    }
}
