package com.practicalchristian.app.core.data.helpers

import com.practicalchristian.app.core.domain.models.DomainError
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Maps infrastructure-level exceptions to domain-level errors.
 * This keeps infrastructure concerns (Room, Retrofit, IO exceptions) out of the domain layer.
 *
 * Use this when catching exceptions in repository implementations.
 */
object ErrorMapper {

    /**
     * Maps a Throwable to a DomainError.
     * This is the bridge between infrastructure failures and business-meaningful errors.
     */
    fun mapToDomainError(throwable: Throwable): DomainError {
        return when (throwable) {
            // Network errors
            is UnknownHostException -> DomainError.Network(
                message = "No internet connection available",
                isOffline = true
            )

            is SocketTimeoutException -> DomainError.Network(
                message = "Connection timed out. Please try again.",
                isOffline = false
            )

            is IOException -> DomainError.Network(
                message = throwable.message ?: "Network error occurred",
                isOffline = false
            )

            // HTTP errors (if using Retrofit)
            // You can add specific HTTP error mapping here
            // is HttpException -> when (throwable.code()) {
            //     401, 403 -> DomainError.Unauthorized
            //     404 -> DomainError.NotFound(throwable.message())
            //     else -> DomainError.Unknown(throwable.message())
            // }

            // Database errors (Room)
            is android.database.sqlite.SQLiteException -> DomainError.Unknown(
                message = "Database error: ${throwable.message}",
                throwable = throwable
            )

            // Validation errors
            is IllegalArgumentException -> DomainError.Validation(
                message = throwable.message ?: "Invalid argument"
            )

            is IllegalStateException -> DomainError.Validation(
                message = throwable.message ?: "Invalid state"
            )

            // Default: Unknown error
            else -> DomainError.Unknown(
                message = throwable.message ?: "An unexpected error occurred",
                throwable = throwable
            )
        }
    }

    /**
     * Extension function to convert Throwable to DomainError.
     * Usage: `exception.toDomainError()`
     */
    fun Throwable.toDomainError(): DomainError = mapToDomainError(this)
}
