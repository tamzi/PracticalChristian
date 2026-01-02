package com.practicalchristian.app.core.localdatasource.helpers

/**
 * Convert a list of entities to their caches without any unchecked casts.
 * The pair constraint (E ↔ C) guarantees type safety for the compiler.
 */
fun <E, C> List<E>.toCaches(): List<C>
        where E : BaseEntity<C>,
              C : BaseCache<E> =
    map { it.toCache() }

/**
 * Convert a list of caches back to their entities without any unchecked casts.
 * The pair constraint (E ↔ C) guarantees type safety for the compiler.
 */
fun <E, C> List<C>.toEntities(): List<E>
        where E : BaseEntity<C>,
              C : BaseCache<E> =
    map { it.toEntity() }
