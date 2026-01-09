package com.practicalchristian.app.core.domain.repository

import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.domain.models.ScheduleDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime

interface ScheduleRepository {

    val schedules: Flow<List<ScheduleDomain>>

    suspend fun getScheduleEntry(scheduleId: Int): Flow<ScheduleDomain>

    suspend fun addScheduleEntry(scheduleId: Int, completedAt: LocalDateTime): Outcome<Boolean>

    suspend fun removeScheduleEntry(scheduleId: Int): Outcome<Boolean>
}
