package com.ku.bazar.productListing.models

enum class Category(val displayName: String) {
    FURNITURE("Furniture"),
    ELECTRONICS("Electronics"),
    INSTRUMENTS("Instruments"),
    CLOTHING("Clothing"),
    BOOKS("Books"),
    OTHER("Other")
}

fun getCategoryDisplayName(category: Category): String {
    return category.displayName
}