package com.practicalchristian.app.core.data.repositories

import com.practicalchristian.app.core.data.mappers.toDomain
import com.practicalchristian.app.core.data.mappers.toDomainError
import com.practicalchristian.app.core.domain.models.DomainError
import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.domain.models.ScheduleDomain
import com.practicalchristian.app.core.domain.repositories.ScheduleRepository
import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntryCache
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.sources.schedule.ScheduleSource
import com.practicalchristian.app.core.localdatasource.sources.schedule_entry.ScheduleEntrySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.datetime.LocalDateTime
import timber.log.Timber
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleSource: ScheduleSource,
    private val scheduleEntrySource: ScheduleEntrySource
) : ScheduleRepository {

    override val schedules: Flow<List<ScheduleDomain>>
        get() = scheduleSource.schedulesWithBooks.mapLatest { caches ->
            caches.map { it.toDomain() }.sortedBy { it.date }
        }

    override suspend fun getScheduleEntry(scheduleId: Int): Flow<ScheduleDomain> {
        return scheduleSource.getScheduleWithBooksFlow(id = scheduleId).mapLatest { it.toDomain() }
    }

    override suspend fun addScheduleEntry(
        scheduleId: Int,
        completedAt: LocalDateTime
    ): Outcome<Boolean> {
        Timber.d("MARKING ITEM AS UNIFINISHED \nSCHEDULE : $scheduleId")
        return when (val result = scheduleEntrySource.exists(scheduleId = scheduleId)) {
            is LocalResult.Error -> Outcome.Failure(error = result.message.toDomainError())
            is LocalResult.Success -> {
                val exists = result.data
                // Business validation: Cannot add duplicate entry
                if (exists) {
                    return Outcome.Failure(
                        error = DomainError.Validation("Schedule entry already exists for this item")
                    )
                }
                when (val insert = scheduleEntrySource.insert(
                    item = ScheduleEntryCache(
                        scheduleId = scheduleId,
                        completedAt = completedAt
                    )
                )) {
                    is LocalResult.Error -> {
                        val domainError = insert.message.toDomainError()
                        Timber.d("ITEM WITH INSERTED WITH ERROR -> $domainError")
                        Outcome.Failure(error = domainError)
                    }

                    is LocalResult.Success -> Outcome.Success(value = true)
                }
            }
        }
    }

    override suspend fun removeScheduleEntry(scheduleId: Int): Outcome<Boolean> {
        return when (val result = scheduleEntrySource.delete(scheduleId = scheduleId)) {
            is LocalResult.Error -> Outcome.Failure(error = result.message.toDomainError())
            is LocalResult.Success -> Outcome.Success(value = result.data)
        }
    }
}
