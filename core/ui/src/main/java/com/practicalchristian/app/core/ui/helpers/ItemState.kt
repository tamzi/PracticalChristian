package com.practicalchristian.app.core.ui.helpers
sealed interface ItemState<out T> {

    data object Loading : ItemState<Nothing>

    data class Error(val message: String?) : ItemState<Nothing>

    data class Success<T>(val item: T) : ItemState<T>
}
