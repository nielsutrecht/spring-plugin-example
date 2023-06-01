package com.nibado.example.plugin.integration

import com.nibado.example.plugin.facade.movies.Actor
import com.nibado.example.plugin.facade.movies.Movie
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class MovieDatabase {
    fun getMovie(id: String): Movie =
        getAllMovies().firstOrNull { it.id == id } ?: throw IllegalArgumentException("Movie with id $id does not exist")

    companion object {
        fun getAllMovies(): List<Movie> =
            listOf(
                Movie(
                    "238",
                    "The Godfather",
                    LocalDate.of(1972, 3, 14),
                    listOf(Actor("3084", "Marlon Brando", LocalDate.of(1924, 4, 3))),
                ),
            )
    }
}
