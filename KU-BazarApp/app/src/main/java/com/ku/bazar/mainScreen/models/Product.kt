package com.ku.bazar.mainScreen.models
import com.google.gson.annotations.SerializedName

data class Product(
    val Item_id: Int,
    val Item_name: String,
    val Item_price: String,
    @SerializedName("Image_url") val imageUrls: List<String>
    // other fields
)
