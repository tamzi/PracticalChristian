package com.practicalchristian.app.core.ui.helpers
sealed interface UiSuccessState<out T> {
    data object Empty : UiSuccessState<Nothing>
    data class Data<T>(val data: T) : UiSuccessState<T>
}

sealed interface UiListState<out T> {

    val hasData: Boolean
        get() = (this is Success) && (this.data is UiSuccessState.Data)

    data class Error(val message: String) : UiListState<Nothing>

    data class Success<T>(val data: UiSuccessState<T>) : UiListState<T>

    data object Loading : UiListState<Nothing>

    data object Idle : UiListState<Nothing>
}
