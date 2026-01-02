package com.practicalchristian.app.core.domain.models

/**
 * Represents different types of errors that can occur in the application
 * TODO: create a Generic fallback error messages enums
 */
sealed class AppError : Throwable {

    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable?) : super(message, cause)

    /**
     * Network-related errors
     */
    data class NetworkError(
        override val message: String,
        val code: Int? = null,
        override val cause: Throwable? = null
    ) : AppError(message, cause)

    /**
     * Database-related errors
     */
    data class DatabaseError(
        override val message: String,
        override val cause: Throwable? = null
    ) : AppError(message, cause)

    /**
     * Validation errors for input data
     */
    data class ValidationError(
        val field: String,
        val validationMessage: String
    ) : AppError("Validation error in $field: $validationMessage")

    /**
     * Authentication/authorization errors
     */
    data class AuthError(
        override val message: String,
        val code: Int? = null
    ) : AppError(message)

    /**
     * Unknown or unexpected errors
     */
    data class UnknownError(
        override val message: String,
        override val cause: Throwable? = null
    ) : AppError(message, cause)

    /**
     * Resource not found errors
     */
    data class NotFoundError(
        val resource: String,
        val id: String? = null
    ) : AppError("$resource not found${id?.let { " with id: $it" } ?: ""}")

    /**
     * Permission denied errors
     */
    data class PermissionError(
        val permission: String,
        val permissionMessage: String = "Permission denied"
    ) : AppError("$permissionMessage: $permission")
}
