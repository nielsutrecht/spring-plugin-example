package com.nibado.example.plugin.facade.config

import com.nibado.example.plugin.facade.movies.Movie
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class EventPublisher {
    private val publishedEvents = mutableListOf<Pair<String, String>>()

    fun update(movie: Movie) {
        log.info("Update event for movie with title {}", movie.title)

        publishedEvents += movie.id to "UPDATE"
    }

    fun delete(id: String) {
        log.info("Delete event for movie with id {}", id)

        publishedEvents += id to "DELETE"
    }

    fun getEvents(): List<Pair<String, String>> = publishedEvents
    fun clearEvents() {
        publishedEvents.clear()
    }

    companion object {
        private val log = LoggerFactory.getLogger(EventPublisher::class.java)
    }
}
