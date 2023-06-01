package com.nibado.example.plugin.config

import com.nibado.example.plugin.facade.config.PluginRegistration
import com.nibado.example.plugin.integration.MovieDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CustomConfig {
    @Bean
    fun pluginRegistration(movieDatabase: MovieDatabase): PluginRegistration {
        return PluginRegistration(
            MovieDatabase::getAllMovies,
            movieDatabase::getMovie,
        )
    }
}
