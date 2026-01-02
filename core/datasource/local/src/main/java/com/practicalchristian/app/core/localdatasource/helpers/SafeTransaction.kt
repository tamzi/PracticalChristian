package com.practicalchristian.app.core.localdatasource.helpers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

suspend fun <T> safeTransaction(
    scope: CoroutineDispatcher = Dispatchers.IO,
    block: suspend () -> T
): LocalResult<T> {
    return withContext(scope) {
        try {
            val data = block()
            LocalResult.Success(data = data)
        } catch (e: Exception) {
            Timber.e(t = e, message = "Error For Safe Transaction")
            LocalResult.Error(message = e.localizedMessage ?: "error")
        }
    }
}
