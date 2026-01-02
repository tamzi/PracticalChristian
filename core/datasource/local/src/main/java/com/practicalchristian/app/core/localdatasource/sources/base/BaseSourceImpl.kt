package com.practicalchristian.app.core.localdatasource.sources.base

import com.practicalchristian.app.core.localdatasource.helpers.BaseCache
import com.practicalchristian.app.core.localdatasource.helpers.BaseEntity
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.helpers.safeTransaction
import com.practicalchristian.app.core.localdatasource.sources.BaseDao

/**
 * A generic base source that persists/reads domain models via a DAO of entities.
 *
 * Type parameters:
 *  - T: the *entity* persisted by the DAO (DB row model)
 *  - R: the *cache* or DTO that the feature layer uses to call this source
 *  - S, V: additional generic parameters (e.g., selection/filter, view model for reads)
 *
 * The key invariants are encoded in the where-clause:
 *  - T : BaseEntity<R>  (entities know how to produce cache R)
 *  - R : BaseCache<T>   (caches know how to recreate entity T)
 *
 * Because of these constraints, no unchecked casts or suppressions are needed.
 */
abstract class BaseSourceImpl<T, R, S, V>(
    private val dao: BaseDao<T>
) : BaseSource<R, S, V>
        where T : BaseEntity<R>,
              R : BaseCache<T> {

    private fun R.asEntity(): T = toEntity() // fully type-safe

    override suspend fun insert(item: R): LocalResult<Long> = safeTransaction {
        dao.insert(item.asEntity())
    }

    override suspend fun insert(vararg item: R): LocalResult<Boolean> = safeTransaction {
        dao.insert(item.map { it.asEntity() })
        true
    }

    override suspend fun update(item: R): LocalResult<Boolean> = safeTransaction {
        dao.update(item.asEntity())
        true
    }

    override suspend fun update(vararg item: R): LocalResult<Boolean> = safeTransaction {
        dao.update(item.map { it.asEntity() })
        true
    }

    override suspend fun delete(item: R): LocalResult<Boolean> = safeTransaction {
        dao.delete(item.asEntity())
        true
    }
}
