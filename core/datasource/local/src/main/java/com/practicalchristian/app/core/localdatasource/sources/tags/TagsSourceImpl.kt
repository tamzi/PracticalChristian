package com.practicalchristian.app.core.localdatasource.sources.tags

import com.practicalchristian.app.core.localdatasource.entity.TagCache
import com.practicalchristian.app.core.localdatasource.entity.TagEntity
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.helpers.safeTransaction
import com.practicalchristian.app.core.localdatasource.helpers.toCaches
import com.practicalchristian.app.core.localdatasource.sources.base.BaseSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import timber.log.Timber
import javax.inject.Inject

class TagsSourceImpl @Inject constructor(
    private val dao: TagsDao
) : TagsSource, BaseSourceImpl<TagEntity, TagCache, Int, String>(dao = dao) {

    override val tags: Flow<List<TagCache>>
        get() = dao.getAllFlow().mapLatest { it.toCaches() }

    override suspend fun get(id: Int): LocalResult<TagCache> = safeTransaction {
        throw Exception("No function found for getting tag by id")
    }

    override suspend fun getFlow(id: Int): LocalResult<Flow<TagCache>> {
        throw Exception("No function found for getting tag by id")
    }

    override suspend fun delete(id: Int): LocalResult<Boolean> = safeTransaction {
        dao.deleteById(id = id)
        true
    }

    override suspend fun getAll(): LocalResult<List<TagCache>> = safeTransaction {
        dao.getAllList().map { it.toCache() }
    }

    override suspend fun getAllFlow(): LocalResult<Flow<List<TagCache>>> = safeTransaction {
        dao.getAllFlow().mapLatest { it.toCaches() }
    }

    override suspend fun deleteAll(): LocalResult<Boolean> = safeTransaction {
        dao.deleteAll()
        true
    }

    override suspend fun search(query: String): LocalResult<Flow<List<TagCache>>> =
        safeTransaction {
            Timber.d("QUERY -> $query")
            dao.search(query = query).mapLatest { it.toCaches() }
        }
}
