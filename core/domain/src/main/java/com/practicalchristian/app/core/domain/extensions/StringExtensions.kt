package com.practicalchristian.app.core.domain.extensions

val String.sentence: String
    get() = split(" ")
        .map { word -> word.replaceFirstChar { char -> char.uppercase() } }
        .joinToString(separator = " ") { it }
