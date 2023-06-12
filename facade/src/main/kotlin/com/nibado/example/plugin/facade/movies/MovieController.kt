package com.nibado.example.plugin.facade.movies

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/movie")
class MovieController(private val movieService: MovieService) {
    @GetMapping
    fun getAll(): MoviesResponse {
        return MoviesResponse(movieService.getAll())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Movie> {
        return ResponseEntity.ofNullable(movieService.getSingle(id))
    }

    @PostMapping
    fun createMovie(@RequestBody movie: Movie): Movie {
        movieService.create(movie)
        return movie
    }

    @PutMapping("/{id}")
    fun updateMovie(@PathVariable id: String, @RequestBody movie: Movie): Movie {
        movieService.update(id, movie)
        return movie
    }

    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id: String): ResponseEntity<Unit> {
        movieService.delete(id)

        return ResponseEntity.noContent().build()
    }

    data class MoviesResponse(val movies: List<Movie>)
}
