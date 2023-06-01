package com.nibado.example.plugin.facade.config

import com.nibado.example.plugin.facade.movies.Movie

class PluginConfiguration(private val pluginRegistration: PluginRegistration) {
    fun getAll(): List<Movie> = pluginRegistration.getAll()
    fun getSingle(id: String): Movie = pluginRegistration.getSingle(id)
}
