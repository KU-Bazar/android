package com.ku.bazar.mainScreen.data

import com.ku.bazar.mainScreen.viewModel.FavoriteItem

data class Products(
    val imageId: Int,
    val title: String,
    val price: String,
    val description: String
)

object data {
    fun getDescriptionForItem(item: FavoriteItem): String {
        // Assuming you have a map of descriptions where the key is the title of the item
        val descriptions = mapOf(
            "Acoustic Guitar" to "This is a good guitar for beginners to kickstart their career in music  ",
            "HDMI2 Cable + TV" to "This cable supports high-definition video and audio.",
            "Mobile" to "Latest smartphone with great camera and performance.",
            "Books" to "Best-selling novels for book lovers.",
            // Add more descriptions as needed
        )

        // Retrieve the description based on the title of
        return descriptions[item.title]
            ?: "Description not available" // Default description if not found
    }
}