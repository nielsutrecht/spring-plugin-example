package com.nibado.example.plugin.facade.movies

import com.nibado.example.plugin.facade.config.PluginConfiguration
import org.springframework.stereotype.Service

@Service
class MovieService(private val config: PluginConfiguration) {
    fun getAll(): List<Movie> = config.getAll()
    fun getSingle(id: String) : Movie  = config.getSingle(id)
}
