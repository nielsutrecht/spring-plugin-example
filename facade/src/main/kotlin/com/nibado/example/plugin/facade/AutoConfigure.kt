package com.nibado.example.plugin.facade

import com.nibado.example.plugin.facade.config.PluginConfiguration
import com.nibado.example.plugin.facade.config.PluginRegistration
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AutoConfigure {
    @Bean
    fun registration(pluginRegistration: PluginRegistration): PluginConfiguration {
        log.info("Found plugin registration with message")

        return PluginConfiguration(pluginRegistration)
    }

    companion object {
        private val log = LoggerFactory.getLogger(AutoConfigure::class.java)
    }
}
