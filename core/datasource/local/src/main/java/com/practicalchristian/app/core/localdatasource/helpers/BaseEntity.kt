package com.practicalchristian.app.core.localdatasource.helpers

/**
 * Marker for a domain/entity type that knows how to create its cache representation.
 *
 * The type parameter R is the *exact* cache type produced by [toCache].
 * Type safety is enforced at usage sites through where clauses:
 *   where T : BaseEntity<R>, R : BaseCache<T>
 */
interface BaseEntity<out R : Any> {
    fun toCache(): R
}
