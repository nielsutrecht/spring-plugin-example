package com.nibado.example.plugin.facade.movies

import com.nibado.example.plugin.facade.config.EventPublisher
import com.nibado.example.plugin.facade.config.PluginRegistration

class MovieService(private val facade: PluginRegistration, private val eventPublisher: EventPublisher) {
    fun getAll(): List<Movie> = facade.getAll()
    fun getSingle(id: String): Movie? = facade.getSingle(id)
    fun update(id: String, movie: Movie) {
        facade.update(id, movie)
        eventPublisher.update(movie)
    }

    fun create(movie: Movie) {
        facade.create(movie)
        eventPublisher.update(movie)
    }

    fun delete(id: String) {
        facade.delete(id)
        eventPublisher.delete(id)
    }
}
