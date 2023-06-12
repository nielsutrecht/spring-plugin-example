package com.nibado.example.plugin.custom.integration

import com.nibado.example.plugin.facade.movies.Movie
import info.movito.themoviedbapi.TmdbApi
import info.movito.themoviedbapi.TmdbMovies
import info.movito.themoviedbapi.model.MovieDb
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class MovieDatabase(private val client: TmdbApi) {
    private val cache = MovieCache()
    fun getMovie(id: String): Movie? {
        return if (cache.isDeleted(id)) {
            null
        } else {
            cache.get(id) ?: findMovie(id)
        }
    }

    fun getAll(): List<Movie> {
        val topMovies =  topMovies()

        return topMovies.map { cache.get(it.id) ?: it }
    }

    fun update(id: String, movie: Movie) {
        cache.update(id, movie)
    }

    fun create(movie: Movie) {
        cache.create(movie)
    }

    fun delete(id: String) {
        cache.delete(id)
    }

    private fun findMovie(id: String): Movie {
        val movieDto = client.movies.getMovie(id.toInt(), "en", TmdbMovies.MovieMethod.credits)

        val movie =  movieDto.toDomain()
        cache.create(movie)

        return movie
    }

    private fun topMovies(): List<Movie> {
        return client.movies.getPopularMovies("en", 0).map { it.toDomain() }
    }

    private fun MovieDb.toDomain() = Movie(id.toString(), title, parseDate(releaseDate))

    companion object {
        private fun parseDate(date: String): LocalDate {
            return LocalDate.parse(date)
        }
    }
}
