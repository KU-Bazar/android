package com.ku.bazar.addProduct

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.call.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.coroutines.*
import android.net.Uri
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import io.ktor.client.features.logging.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.ui.theme.PrimaryPink
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.io.FileOutputStream
import coil.compose.rememberAsyncImagePainter
import com.ku.bazar.R
import com.ku.bazar.addProduct.models.Product
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

import kotlinx.coroutines.launch


enum class Category {
    Books, Clothes, Electronics, Furniture, Other
}
@Composable
fun addProduct() {
    var selectedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var isUploading by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris: List<Uri> -> selectedImageUris = uris }
    )

    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = -300.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_background),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 45.dp,
                            bottomEnd = 45.dp
                        )
                    )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 30.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Add Product",
                fontSize = 20.sp,
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 90.dp, x = 120.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.topographic_6),
                contentDescription = "Pattern",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .offset(y = 20.dp, x = 20.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { }
                    .background(color = Color(0xFFF5F5F5))
                    .padding(10.dp),
                tint = Color.Black
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .offset(y = -140.dp)
                .padding(16.dp)
        ) {
            Text(
                text = "Image Previews",
                color = Color(0xFFB1B1B1),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (selectedImageUris.isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_preview),
                    contentDescription = "Image Preview Placeholder",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(selectedImageUris) { uri ->
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color(0xFFD9D9D9))
                                .clip(RoundedCornerShape(16.dp))
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(uri),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .offset(y = 350.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            // Product Name TextField
            SearchBarWithProductName("Product Name", productName) { productName = it }

            Spacer(modifier = Modifier.height(16.dp))

            // Price TextField
            SearchBarWithProductName(
                "Price",
                productPrice,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            ) { productPrice = it }

            Spacer(modifier = Modifier.height(16.dp))

            // Description TextField with maxLines
            SearchBarWithProductName(
                "Description",
                productDescription,
                maxLines = 5
            ) { productDescription = it }

            Spacer(modifier = Modifier.height(16.dp))

            // Category Dropdown
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable{expanded = true}
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(4.dp))
                    .padding(vertical = 4.dp, horizontal = 4.dp)
            ) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Category.values().forEach { category ->
                        DropdownMenuItem(
                            onClick = {
                                selectedCategory = category
                                expanded = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(text = category.name)
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Selected Category: ${selectedCategory?.name ?: "Select Category"}",
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                // Add Images Button
                Button(
                    onClick = { launcher.launch("image/*") },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryPink,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(text = "Add Images", modifier = Modifier.padding(end = 12.dp))
                }

                // Upload Product Button
                Button(
                    onClick = {
                        if (!isUploading) { // Check if upload is not already in progress
                            isUploading = true // Set flag to true to indicate upload started
                            coroutineScope.launch {
                                try {
                                    // Upload product
                                    selectedCategory?.let { category ->
                                        uploadProduct(
                                            context = context,
                                            selectedImageUris = selectedImageUris,
                                            productName = productName,
                                            productDescription = productDescription,
                                            productPrice = productPrice.toInt(),
                                            category = category.name
                                        )
                                    }
                                } finally {
                                    isUploading = false // Reset flag after upload attempt
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = PrimaryPink
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Text(text = "Upload Product", modifier = Modifier.padding(end = 12.dp))
                }
            }
        }
    }
    }
@Composable
fun SearchBarWithProductName(
    title: String,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = 1,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .width(350.dp)
            .padding(8.dp)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color(0xFFB1B1B1),
            modifier = Modifier.padding(bottom = 2.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text("") },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = keyboardOptions,
            maxLines = maxLines,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF5F5F5),
                focusedIndicatorColor = PrimaryPink,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}


fun uriToFile(uri: Uri, context: Context): File {
    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
    val file = File(context.cacheDir, "upload_${System.currentTimeMillis()}.jpg")
    val outputStream: OutputStream = FileOutputStream(file)
    inputStream?.use { input ->
        outputStream.use { output ->
            input.copyTo(output)
        }
    }
    return file
}

suspend fun uploadProduct(
    context: Context,
    selectedImageUris: List<Uri>,
    productName: String,
    productDescription: String,
    productPrice: Int,
    category: String
) {
    Log.d("UploadProduct", "Product Name: $productName")
    Log.d("UploadProduct", "Product Description: $productDescription")
    Log.d("UploadProduct", "Product Price: $productPrice")
    Log.d("UploadProduct", "Selected Image URIs: $selectedImageUris")

    val client = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
    }

    try {
        val product = Product(
            Item_name = productName,
            Item_desc = productDescription,
            Item_price = productPrice,
            Image_url = null,  // Placeholder or null if not available
            Item_id = 0,  // Placeholder or 0 if not available
            Item_seller = "Admin",  // Default value or placeholder
            category = category// Default value or placeholder
        )

        val formData = formData {
            append("item_name", productName)
            append("item_desc", productDescription)
            append("item_price", productPrice.toString()) // Convert Int to String
            append("item_seller", "Admin") // Assuming static seller name
            append("category", category)

            selectedImageUris.forEachIndexed { index, uri ->
                val file = uriToFile(uri, context)
                append(
                    "files",
                    file.readBytes(),
                    Headers.build {
                        append(HttpHeaders.ContentType, ContentType.Application.OctetStream.toString())
                        append(HttpHeaders.ContentDisposition, "filename=\"${file.name}\"")
                    }
                )
            }
        }

        val response: HttpResponse = client.submitFormWithBinaryData(
            url = "https://fine-moral-seasnail.ngrok-free.app/upload",
            formData = formData
        )

        // Handle the response
        val responseBody = response.readText()
        println("Upload success: $responseBody")
        // Check if response indicates success
        response.status == HttpStatusCode.OK
    } catch (e: Exception) {
        println("Upload failed: ${e.message}")
        false
    } finally {
        client.close()
    }
}

