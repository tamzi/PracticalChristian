package com.practicalchristian.app.core.ui.helpers

import com.practicalchristian.app.core.domain.models.DomainError
import com.practicalchristian.app.core.domain.models.Outcome

/**
 * Converts a DomainError to a user-friendly error message.
 * This is where we translate business errors into UI-appropriate text.
 */
fun DomainError.toUserMessage(): String {
    return when (this) {
        is DomainError.Network -> {
            if (isOffline) {
                "No internet connection. Please check your network settings."
            } else {
                message
            }
        }

        is DomainError.NotFound -> "The requested item could not be found."
        is DomainError.Validation -> message
        is DomainError.Unauthorized -> "You are not authorized to perform this action."
        is DomainError.Unknown -> message.ifBlank { "An unexpected error occurred. Please try again." }
    }
}

/**
 * Maps an Outcome to an ItemState for single-item UI states.
 */
fun <T> Outcome<T>.toItemState(): ItemState<T> {
    return when (this) {
        is Outcome.Success -> ItemState.Success(item = value)
        is Outcome.Failure -> ItemState.Error(message = error.toUserMessage())
    }
}
