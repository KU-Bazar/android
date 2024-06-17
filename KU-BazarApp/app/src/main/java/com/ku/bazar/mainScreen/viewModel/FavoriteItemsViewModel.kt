package com.ku.bazar.mainScreen.viewModel


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class FavoriteItemsViewModel : ViewModel() {
    private val _favoriteItems = mutableStateListOf<FavoriteItem>()
    val favoriteItems: List<FavoriteItem> = _favoriteItems

    fun toggleFavorite(item: FavoriteItem) {


        if (_favoriteItems.contains(item)) {
            _favoriteItems.remove(item)
        } else {
            _favoriteItems.add(item)
        }

    }

    fun isFavorite(item: FavoriteItem): Boolean {


        return _favoriteItems.contains(item)
    }
}

data class FavoriteItem(val imageId: Int, val title: String, val price: String, val description: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FavoriteItem) return false

        if (imageId != other.imageId) return false
        if (title != other.title) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = imageId
        result = 31 * result + title.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }
}
