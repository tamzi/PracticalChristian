@file:OptIn(ExperimentalTime::class)

package com.practicalchristian.app.core.localdatasource.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.practicalchristian.app.core.localdatasource.entity.ScheduleCache
import com.practicalchristian.app.core.localdatasource.entity.ScheduleCacheItem
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.helpers.sequential
import com.practicalchristian.app.core.localdatasource.helpers.toInstant
import com.practicalchristian.app.core.localdatasource.sources.schedule.ScheduleSource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import java.util.UUID
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun Context.startSequentialScheduleWork(from: Long = Clock.System.now().toEpochMilliseconds()) {
    val uuid = UUID.randomUUID()

    val constraints = Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .setRequiresStorageNotLow(true)
        .build()

    val parameters = Data.Builder()
        .putLong(SequentialScheduleWorker.KEY_DATE, from)
        .build()

    val request = OneTimeWorkRequestBuilder<SequentialScheduleWorker>()
        .addTag(SequentialScheduleWorker.TAG)
        .setConstraints(constraints)
        .setInputData(parameters)
        .setId(uuid)
        .build()

    WorkManager.getInstance(this).enqueue(request)
}

@HiltWorker
class SequentialScheduleWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val params: WorkerParameters,
    private val sources: ScheduleSource,
) : CoroutineWorker(context, params) {

    companion object {
        const val TAG = "SEQUENTIAL_SCHEDULE_WORKER"
        const val KEY_DATE = "DATE"
    }

    override suspend fun doWork(): Result {
        val from = inputData.getLong(KEY_DATE, -1L)
        if (from == -1L) Result.failure()
        val instant = from.toInstant()
        val schedules = sequential.mapIndexed { index, sequence ->
            Pair(sequence, instant.plus(DateTimePeriod(days = index), TimeZone.UTC))
        }
        for ((schedule, date) in schedules) {
            val cache = ScheduleCache(
                start = ScheduleCacheItem(
                    book = schedule.start.book - 1,
                    chapter = schedule.start.chapter
                ),
                end = ScheduleCacheItem(
                    book = schedule.end.book - 1,
                    chapter = schedule.end.chapter
                ),
                date = date.toEpochMilliseconds()
            )
            val result = sources.insert(cache)
            if (result is LocalResult.Error) {
                sources.deleteAll()
                return if (params.runAttemptCount > 1)
                    Result.failure()
                else
                    Result.retry()
            }
        }

        return Result.success()
    }
}
