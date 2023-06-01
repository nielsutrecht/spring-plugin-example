package com.nibado.example.plugin.integration

import com.nibado.example.plugin.facade.config.EventPublisher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/webhook")
class MovieUpdateWebhook(private val movieDatabase: MovieDatabase, private val eventPublisher: EventPublisher) {
    @PostMapping("/movie/{id}")
    fun movieUpdated(@PathVariable id: String): ResponseEntity<Unit> {
        val movie = movieDatabase.getMovie(id)

        eventPublisher.update(movie)

        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/movie/{id}")
    fun movieDeleted(@PathVariable id: String): ResponseEntity<Unit> {
        eventPublisher.delete(id)

        return ResponseEntity.noContent().build()
    }
}
