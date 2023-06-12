package com.nibado.example.plugin.custom

import com.nibado.example.plugin.facade.config.EventPublisher
import com.nibado.example.plugin.facade.movies.Movie
import com.nibado.example.plugin.facade.movies.MovieController
import com.nibado.example.plugin.facade.movies.MovieService
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = ["local", "integration"])
abstract class BaseIntegrationTests {
    @Autowired
    lateinit var eventPublisher: EventPublisher

    @Autowired
    lateinit var template: TestRestTemplate

    @Autowired
    lateinit var movieService: MovieService

    @BeforeEach
    fun setup() {
        movieService.delete("123")
        movieService.delete("321")
        eventPublisher.clearEvents()
    }

    @TestFactory
    fun `Should be able to get movies by ID`() = listOf(
        "603692" to "John Wick: Chapter 4",
        "502356" to "The Super Mario Bros. Movie",
        "324857" to "Spider-Man: Into the Spider-Verse",
    ).map { (id, title) ->
        DynamicTest.dynamicTest("$id: $title") {
            val response = template.getForEntity<Movie>("/movie/{id}", id)

            assertThat(response.statusCode).isEqualTo(HttpStatus.OK)

            val movie = response.body!!

            assertThat(movie.id).isEqualTo(id)
            assertThat(movie.title).isEqualTo(title)
        }
    }

    @Test
    fun `Should be able to get all movies`() {
        val response = template.getForEntity<MovieController.MoviesResponse>("/movie")
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)

        val movies = response.body!!.movies

        assertThat(movies).hasSize(20)

        with(movies.first()) {
            assertThat(id).isEqualTo("603692")
        }
    }

    @Test
    fun `Should be able to create, update and delete a movie`() {
        assertThat(movieService.getSingle("123")).isNull()

        val newMovie = Movie("123", "My Movie", LocalDate.now())

        val responseCreate = template.postForEntity<Movie>("/movie", newMovie)

        assertThat(responseCreate.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseCreate.body!!.id).isEqualTo("123")

        val created = movieService.getSingle("123") ?: fail("Should exist")

        assertThat(created.id).isEqualTo("123")
        assertThat(created.title).isEqualTo("My Movie")

        val updatedMovie = newMovie.copy(id = "321", title = "My Updated Movie")

        template.put("/movie/123", updatedMovie)

        assertThat(movieService.getSingle("123")).isNull()
        assertThat(movieService.getSingle("321")?.title).isEqualTo("My Updated Movie")

        template.delete("/movie/321")

        assertThat(movieService.getSingle("321")).isNull()

        val events = eventPublisher.getEvents()

        assertThat(events).containsExactly(
            "123" to "UPDATE",
            "321" to "UPDATE",
            "321" to "DELETE",
        )
    }
}
