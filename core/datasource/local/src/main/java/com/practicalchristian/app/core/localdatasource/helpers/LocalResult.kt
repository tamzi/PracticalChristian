package com.practicalchristian.app.core.localdatasource.helpers

sealed interface LocalResult<out T> {
    data class Error(val message: String) : LocalResult<Nothing>

    data class Success<T>(val data: T) : LocalResult<T>
}
