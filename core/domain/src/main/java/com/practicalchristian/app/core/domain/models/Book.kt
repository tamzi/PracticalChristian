package com.practicalchristian.app.core.domain.models

data class Book(val id: Int, val name: String, val chapters: Int) {
    operator fun minus(book: Book) = id - book.id
}
