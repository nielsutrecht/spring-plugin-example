package com.nibado.example.plugin.facade.standardplugin

import com.nibado.example.plugin.facade.movies.Movie

class StandardMovieDatabase(data: List<Movie>) {
    private val movies = mutableListOf<Movie>()

    init {
        movies += data
    }
    fun getSingle(id: String): Movie? {
        return movies.find { it.id == id }
    }

    fun getAll(): List<Movie> {
        return movies
    }

    fun update(id: String, movie: Movie) {
        movies.removeIf { it.id == id }
        movies += movie
    }

    fun create(movie: Movie) {
        movies += movie
    }

    fun delete(id: String) {
        movies.removeIf { it.id == id }
    }
}
