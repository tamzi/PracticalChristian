package com.practicalchristian.app.core.localdatasource.sources.schedule

import com.practicalchristian.app.core.localdatasource.entity.ScheduleCache
import com.practicalchristian.app.core.localdatasource.entity.relations.ScheduleWithBookCache
import com.practicalchristian.app.core.localdatasource.sources.base.BaseSource
import kotlinx.coroutines.flow.Flow

interface ScheduleSource : BaseSource<ScheduleCache, Int, String> {

    val schedules: Flow<List<ScheduleCache>>

    val schedulesWithBooks: Flow<List<ScheduleWithBookCache>>

    suspend fun getScheduleWithBooksFlow(id: Int): Flow<ScheduleWithBookCache>
}
