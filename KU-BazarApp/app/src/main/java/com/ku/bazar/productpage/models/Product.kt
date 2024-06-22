package com.ku.bazar.productpage.models
import com.google.gson.annotations.SerializedName
import com.ku.bazar.productListing.models.Category

data class Product(
    val Item_id: Int,
    val Item_name: String,
    val Item_desc: String,
    val Item_price: Int,
    val Item_seller: String,
    //val category: String,
    @SerializedName("Image_url") val Image_url: List<String>?,
    val category: Category
//    var isFavorite: Boolean = false
)
