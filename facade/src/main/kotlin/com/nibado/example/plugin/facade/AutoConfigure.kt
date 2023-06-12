package com.nibado.example.plugin.facade

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nibado.example.plugin.facade.config.EventPublisher
import com.nibado.example.plugin.facade.config.PluginRegistration
import com.nibado.example.plugin.facade.movies.Movie
import com.nibado.example.plugin.facade.movies.MovieController
import com.nibado.example.plugin.facade.movies.MovieService
import com.nibado.example.plugin.facade.standardplugin.StandardMovieDatabase
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.nibado.example.plugin.facade")
class AutoConfigure {
    @ConditionalOnProperty(
        value = ["facade"],
        havingValue = "standard",
        matchIfMissing = true,
    )
    @Bean
    fun standardPluginRegistration(objectMapper: ObjectMapper): PluginRegistration {
        log.info("Configuring standard plugin")

        val standardMovieDatabase = StandardMovieDatabase(readDefaultData(objectMapper))

        return PluginRegistration(
            standardMovieDatabase::getAll,
            standardMovieDatabase::getSingle,
            standardMovieDatabase::create,
            standardMovieDatabase::update,
            standardMovieDatabase::delete,
        )
    }

    private fun readDefaultData(objectMapper: ObjectMapper): List<Movie> = objectMapper
        .readValue<MovieController.MoviesResponse>(AutoConfigure::class.java.getResourceAsStream("/data/movies.json"))
        .movies

    @Bean
    fun service(eventPublisher: EventPublisher, pluginRegistration: PluginRegistration): MovieService {
        log.info("Found plugin registration")

        return MovieService(pluginRegistration, eventPublisher)
    }

    companion object {
        private val log = LoggerFactory.getLogger(AutoConfigure::class.java)
    }
}
