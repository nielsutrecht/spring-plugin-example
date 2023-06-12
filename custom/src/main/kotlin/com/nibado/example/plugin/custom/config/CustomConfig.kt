package com.nibado.example.plugin.custom.config

import com.nibado.example.plugin.custom.integration.MovieDatabase
import com.nibado.example.plugin.facade.AutoConfigure
import com.nibado.example.plugin.facade.config.PluginRegistration
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(
    value = ["facade"],
    havingValue = "custom",
    matchIfMissing = false,
)
class CustomConfig {
    @Bean
    fun pluginRegistration(movieDatabase: MovieDatabase): PluginRegistration {
        log.info("Configuring custom plugin")

        return PluginRegistration(
            movieDatabase::getAll,
            movieDatabase::getMovie,
            movieDatabase::create,
            movieDatabase::update,
            movieDatabase::delete,
        )
    }

    companion object {
        private val log = LoggerFactory.getLogger(AutoConfigure::class.java)
    }
}
