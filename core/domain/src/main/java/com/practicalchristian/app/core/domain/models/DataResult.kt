package com.practicalchristian.app.core.domain.models

/**
 * Represents the outcome of a domain operation.
 * Only contains business-level results - no UI states like Loading/Idle/Empty.
 *
 * UI lifecycle states (Loading, Idle, Empty) are derived in the presentation layer.
 * Infrastructure errors are mapped to DomainError in the data layer.
 */
sealed interface Outcome<out T> {
    /**
     * Successful domain operation with a value.
     * Note: Empty collections should be Success(emptyList()), not a separate Empty state.
     */
    data class Success<T>(val value: T) : Outcome<T>

    /**
     * Failed domain operation with a business-meaningful error.
     * Infrastructure exceptions (network, database) are mapped to DomainError in the data layer.
     */
    data class Failure(val error: DomainError) : Outcome<Nothing>
}

/**
 * Domain-level errors that are business-meaningful.
 * Do not expose infrastructure types (Retrofit, Room exceptions, etc.) here.
 */
sealed interface DomainError {
    /**
     * Network-related issues (offline, timeout, etc.)
     */
    data class Network(val message: String, val isOffline: Boolean = false) : DomainError

    /**
     * Resource not found (404, missing entity, etc.)
     */
    data class NotFound(val message: String) : DomainError

    /**
     * Validation or business rule violation
     */
    data class Validation(val message: String) : DomainError

    /**
     * Authentication/Authorization issues
     */
    data object Unauthorized : DomainError

    /**
     * Generic error for unexpected issues
     */
    data class Unknown(val message: String, val throwable: Throwable? = null) : DomainError
}
