package com.ku.bazar.mainScreen.models
import com.google.gson.annotations.SerializedName

data class Product(
    val Item_id: Int,
    val Item_name: String,
    val Item_desc: String,
    val Item_price: Int,
    val Item_seller: String,
    @SerializedName("Image_url") val Image_url: List<String>?,
//    var isFavorite: Boolean = false
)
