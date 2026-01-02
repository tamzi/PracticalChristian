package com.practicalchristian.app.core.localdatasource.sources.schedule

import com.practicalchristian.app.core.localdatasource.entity.ScheduleCache
import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntity
import com.practicalchristian.app.core.localdatasource.entity.relations.ScheduleWithBookCache
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.helpers.safeTransaction
import com.practicalchristian.app.core.localdatasource.helpers.toCaches
import com.practicalchristian.app.core.localdatasource.sources.base.BaseSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class ScheduleSourceImpl @Inject constructor(
    private val dao: ScheduleDao
) : ScheduleSource, BaseSourceImpl<ScheduleEntity, ScheduleCache, Int, String>(dao = dao) {

    override val schedules: Flow<List<ScheduleCache>>
        get() = dao.getAllFlow().mapLatest { it.toCaches() }

    override val schedulesWithBooks: Flow<List<ScheduleWithBookCache>>
        get() = dao.getSchedulesWithBooks().mapLatest { list -> list.map { it.toCache() } }

    override suspend fun getScheduleWithBooksFlow(id: Int): Flow<ScheduleWithBookCache> {
        return dao.getScheduleWithBook(id = id).mapLatest { it.toCache() }
    }

    override suspend fun get(id: Int): LocalResult<ScheduleCache> = safeTransaction {
        dao.get(id = id).toCache()
    }

    override suspend fun delete(id: Int): LocalResult<Boolean> = safeTransaction {
        dao.deleteById(id = id)
        true
    }

    override suspend fun getFlow(id: Int): LocalResult<Flow<ScheduleCache>> = safeTransaction {
        dao.getFlow(id = id).mapLatest { it.toCache() }
    }

    override suspend fun getAllFlow(): LocalResult<Flow<List<ScheduleCache>>> = safeTransaction {
        dao.getAllFlow().mapLatest { it.toCaches() }
    }

    override suspend fun getAll(): LocalResult<List<ScheduleCache>> = safeTransaction {
        dao.getAll().toCaches()
    }

    override suspend fun deleteAll(): LocalResult<Boolean> = safeTransaction {
        dao.deleteAll()
        true
    }

    override suspend fun search(query: String): LocalResult<Flow<List<ScheduleCache>>> =
        safeTransaction {
            MutableStateFlow(listOf())
        }
}
