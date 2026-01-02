package com.practicalchristian.app.core.localdatasource.sources.schedule_entry

import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntryCache
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.sources.base.BaseSource

interface ScheduleEntrySource : BaseSource<ScheduleEntryCache, String, String> {

    suspend fun exists(scheduleId: Int): LocalResult<Boolean>

    suspend fun delete(scheduleId: Int): LocalResult<Boolean>
}
