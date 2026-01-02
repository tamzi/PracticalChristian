package com.practicalchristian.app.core.localdatasource.sources.schedule_entry

import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntryCache
import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntryEntity
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.helpers.safeTransaction
import com.practicalchristian.app.core.localdatasource.helpers.toCaches
import com.practicalchristian.app.core.localdatasource.sources.base.BaseSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class ScheduleEntrySourceImpl @Inject constructor(
    private val dao: ScheduleEntryDao
) : ScheduleEntrySource,
    BaseSourceImpl<ScheduleEntryEntity, ScheduleEntryCache, String, String>(dao = dao) {

    override suspend fun exists(scheduleId: Int): LocalResult<Boolean> = safeTransaction {
        dao.getEntriesBySchedule(id = scheduleId).isNotEmpty()
    }

    override suspend fun delete(scheduleId: Int): LocalResult<Boolean> = safeTransaction {
        dao.deleteByScheduleId(id = scheduleId)
        true
    }

    override suspend fun get(id: String): LocalResult<ScheduleEntryCache> =
        safeTransaction {
            dao.get(id = id).toCache()
        }

    override suspend fun getFlow(id: String): LocalResult<Flow<ScheduleEntryCache>> =
        safeTransaction {
            dao.getFlow(id = id).mapLatest { it.toCache() }
        }

    override suspend fun getAll(): LocalResult<List<ScheduleEntryCache>> =
        safeTransaction {
            dao.getAll().map { it.toCache() }
        }

    override suspend fun getAllFlow(): LocalResult<Flow<List<ScheduleEntryCache>>> =
        safeTransaction {
            dao.getAllFlow().mapLatest { it.toCaches() }
        }

    override suspend fun search(query: String): LocalResult<Flow<List<ScheduleEntryCache>>> =
        safeTransaction {
            MutableStateFlow(listOf())
        }

    override suspend fun deleteAll(): LocalResult<Boolean> =
        safeTransaction {
            dao.deleteAll()
            true
        }

    override suspend fun delete(id: String): LocalResult<Boolean> =
        safeTransaction {
            dao.deleteById(id = id)
            true
        }
}
