@file:OptIn(ExperimentalTime::class)

package com.practicalchristian.app.core.localdatasource.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.practicalchristian.app.core.localdatasource.entity.BookCache
import com.practicalchristian.app.core.localdatasource.helpers.BibleBook
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.helpers.instant
import com.practicalchristian.app.core.localdatasource.sources.book.BookSources
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.datetime.LocalDateTime
import java.util.UUID
import kotlin.time.ExperimentalTime

fun Context.startPopulateBibleBooksWork() {
    val uuid = UUID.randomUUID()

    val constraints = Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .setRequiresStorageNotLow(true)
        .build()

    val request = OneTimeWorkRequestBuilder<PopulateBibleBooksWorker>()
        .setConstraints(constraints)
        .addTag(PopulateBibleBooksWorker.TAG)
        .setId(uuid)
        .build()

    WorkManager.getInstance(this).enqueue(request)
}

@HiltWorker
class PopulateBibleBooksWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val params: WorkerParameters,
    private val sources: BookSources,
) : CoroutineWorker(context, params) {

    companion object {
        const val TAG = "POPULATE_BIBLE_BOOKS_WORKER"
    }

    override suspend fun doWork(): Result {
        val values = BibleBook.entries.withIndex()
        for ((index, book) in values) {
            val cache = BookCache(id = index, name = book.value, chapters = book.chapters)
            val result = sources.insert(cache)
            if (result is LocalResult.Error) {
                sources.deleteAllBooks()
                return if (params.runAttemptCount > 1)
                    Result.failure()
                else
                    Result.retry()
            }
        }

        context.startSequentialScheduleWork(
            LocalDateTime(
                2024,
                1,
                1,
                0,
                0,
                0,
                0
            ).instant.toEpochMilliseconds()
        )
        return Result.success()
    }
}
