package com.practicalchristian.app.core.ui.helpers

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Base ViewModel class with built-in state management.
 *
 * Provides a simple pattern for managing UI state in ViewModels.
 * Uses standard Android ViewModel with Kotlin StateFlow.
 *
 * @param initial The initial state value
 */
open class StatefulViewModel<T>(initial: T) : ViewModel() {

    private val _state = MutableStateFlow(initial)
    val state get() = _state.asStateFlow()

    protected fun update(value: T) {
        _state.value = value
    }

    protected fun update(block: T.() -> T) {
        update(state.value.block())
    }
}
