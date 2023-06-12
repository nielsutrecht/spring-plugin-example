package com.nibado.example.plugin.custom.integration

import info.movito.themoviedbapi.TmdbApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MovieDbConfig {
    @Bean
    fun client(@Value("\${themoviedb.api-key}") apiKey: String): TmdbApi {
        return TmdbApi(apiKey)
    }
}
