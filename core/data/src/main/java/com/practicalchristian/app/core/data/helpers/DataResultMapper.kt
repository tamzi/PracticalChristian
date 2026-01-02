package com.practicalchristian.app.core.data.helpers

import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.data.helpers.ErrorMapper.toDomainError
import timber.log.Timber

/**
 * Provides safe execution wrappers for repository operations.
 * Automatically catches exceptions and maps them to Outcome with DomainError.
 *
 * This eliminates repetitive try-catch blocks in repository implementations.
 */
object DataResultMapper {

    /**
     * Executes a suspend block and wraps result in Outcome.
     * Catches any exception and maps to Outcome.Failure with DomainError.
     *
     * This is the recommended approach - provides business-meaningful errors.
     *
     * Usage:
     * ```
     * override suspend fun getUser(id: String): Outcome<User> = safeOutcome {
     *     val user = api.getUser(id)
     *     user.toDomain()
     * }
     * ```
     */
    suspend fun <T> safeOutcome(block: suspend () -> T): Outcome<T> {
        return try {
            val result = block()
            Outcome.Success(value = result)
        } catch (e: Exception) {
            Timber.e(e, "Repository operation failed")
            Outcome.Failure(error = e.toDomainError())
        }
    }

    /**
     * Executes a non-suspend block and wraps result in Outcome.
     * Use for synchronous operations.
     */
    fun <T> safeOutcomeSync(block: () -> T): Outcome<T> {
        return try {
            val result = block()
            Outcome.Success(value = result)
        } catch (e: Exception) {
            Timber.e(e, "Repository operation failed")
            Outcome.Failure(error = e.toDomainError())
        }
    }
}

/**
 * Extension function for cleaner syntax.
 *
 * Usage:
 * ```
 * override suspend fun getUser(id: String): Outcome<User> = safeOutcome {
 *     api.getUser(id).toDomain()
 * }
 * ```
 */
suspend fun <T> safeOutcome(block: suspend () -> T): Outcome<T> =
    DataResultMapper.safeOutcome(block)

/**
 * Extension function for synchronous operations.
 */
fun <T> safeOutcomeSync(block: () -> T): Outcome<T> =
    DataResultMapper.safeOutcomeSync(block)
