package com.nibado.example.plugin.custom.integration

import com.nibado.example.plugin.facade.movies.Movie

class MovieCache {
    private val deleted = mutableSetOf<String>()
    private val movies = mutableListOf<Movie>()

    fun get(id: String): Movie? = movies.find { it.id == id }
    fun create(movie: Movie) {
        movies += movie
        deleted -= movie.id
    }

    fun update(id: String, movie: Movie) {
        movies.removeIf { it.id == id }
        movies += movie
        deleted += id
        deleted -= movie.id
    }

    fun delete(id: String) {
        movies.removeIf { it.id == id }
        deleted += id
    }

    fun isDeleted(id: String) = deleted.contains(id)
}
