package com.practicalchristian.app.core.localdatasource.sources.notes

import com.practicalchristian.app.core.localdatasource.entity.NoteCache
import com.practicalchristian.app.core.localdatasource.entity.NoteEntity
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.helpers.safeTransaction
import com.practicalchristian.app.core.localdatasource.helpers.toCaches
import com.practicalchristian.app.core.localdatasource.sources.base.BaseSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class NotesSourceImpl @Inject constructor(
    private val dao: NotesDao
) : NotesSource,
    BaseSourceImpl<NoteEntity, NoteCache, String, String>(dao = dao) {

    override val notes: Flow<List<NoteCache>>
        get() = dao.getAllFlow().mapLatest { it.toCaches() }

    override suspend fun get(id: String): LocalResult<NoteCache> = safeTransaction {
        dao.get(id = id).toCache()
    }

    override suspend fun getFlow(id: String): LocalResult<Flow<NoteCache>> = safeTransaction {
        dao.getFlow(id = id).mapLatest { it.toCache() }
    }

    override suspend fun delete(id: String): LocalResult<Boolean> = safeTransaction {
        dao.deleteById(id = id)
        true
    }

    override suspend fun getAll(): LocalResult<List<NoteCache>> = safeTransaction {
        dao.getAll().map { it.toCache() }
    }

    override suspend fun getAllFlow(): LocalResult<Flow<List<NoteCache>>> = safeTransaction {
        dao.getAllFlow().mapLatest { it.toCaches() }
    }

    override suspend fun deleteAll(): LocalResult<Boolean> = safeTransaction {
        dao.deleteAll()
        true
    }

    override suspend fun search(query: String): LocalResult<Flow<List<NoteCache>>> =
        safeTransaction {
            dao.search(query = query).mapLatest { it.toCaches() }
        }
}
