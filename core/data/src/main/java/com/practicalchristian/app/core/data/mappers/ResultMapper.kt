package com.practicalchristian.app.core.data.mappers

import com.practicalchristian.app.core.domain.models.DomainError
import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult

/**
 * Maps infrastructure result (LocalResult) to domain result (Outcome).
 * This is where we translate infrastructure concerns to business-level outcomes.
 *
 * @param block Optional transformation of the success value
 */
inline fun <T, reified R> LocalResult<T>.toOutcome(block: (T) -> R): Outcome<R> {
    return when (this) {
        is LocalResult.Error -> Outcome.Failure(error = this.message.toDomainError())
        is LocalResult.Success -> {
            val result = this.data
            if (result is R)
                Outcome.Success(value = result)
            else
                Outcome.Success(value = block(data))
        }
    }
}

/**
 * Maps infrastructure result to domain result without transformation.
 */
fun <T> LocalResult<T>.toOutcome(): Outcome<T> {
    return when (this) {
        is LocalResult.Error -> Outcome.Failure(error = this.message.toDomainError())
        is LocalResult.Success -> Outcome.Success(value = data)
    }
}

/**
 * Maps infrastructure error message to domain error.
 * This encapsulates the logic of determining what kind of domain error occurred
 * based on the infrastructure error message.
 */
fun String.toDomainError(): DomainError {
    return when {
        this.contains("not found", ignoreCase = true) ||
                this.contains("does not exist", ignoreCase = true) ->
            DomainError.NotFound(message = this)

        this.contains("network", ignoreCase = true) ||
                this.contains("connection", ignoreCase = true) ||
                this.contains("timeout", ignoreCase = true) ->
            DomainError.Network(
                message = this,
                isOffline = this.contains("offline", ignoreCase = true)
            )

        this.contains("unauthorized", ignoreCase = true) ||
                this.contains("authentication", ignoreCase = true) ||
                this.contains("permission", ignoreCase = true) ->
            DomainError.Unauthorized

        this.contains("invalid", ignoreCase = true) ||
                this.contains("validation", ignoreCase = true) ->
            DomainError.Validation(message = this)

        else -> DomainError.Unknown(message = this, throwable = null)
    }
}
