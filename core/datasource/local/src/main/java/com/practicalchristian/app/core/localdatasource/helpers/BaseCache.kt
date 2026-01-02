package com.practicalchristian.app.core.localdatasource.helpers

/**
 * Marker for a cache (DB/DTO) type that knows how to recreate its domain/entity counterpart.
 *
 * The type parameter T is the *exact* entity type produced by [toEntity].
 * Type safety is enforced at usage sites through where clauses:
 *   where T : BaseEntity<R>, R : BaseCache<T>
 */
interface BaseCache<out T : Any> {
    fun toEntity(): T
}
