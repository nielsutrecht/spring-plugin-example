package com.nibado.example.plugin.facade.config

import com.nibado.example.plugin.facade.movies.Movie

class PluginRegistration(
    val getAll: () -> List<Movie>,
    val getSingle: (String) -> Movie?,
    val create: (Movie) -> Unit,
    val update: (String, Movie) -> Unit,
    val delete: (String) -> Unit,
)
