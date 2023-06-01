package com.nibado.example.plugin.facade.config

import com.nibado.example.plugin.facade.movies.Movie
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class EventPublisher {
    fun update(movie: Movie) {
        log.info("Update event for movie with title {}", movie.title)
    }

    fun delete(id: String) {
        log.info("Delete event for movie with id {}", id)
    }

    companion object {
        private val log = LoggerFactory.getLogger(EventPublisher::class.java)
    }
}
