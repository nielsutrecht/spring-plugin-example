package com.nibado.example.plugin.custom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PluginExampleApplication

fun main(args: Array<String>) {
	runApplication<PluginExampleApplication>(*args)
}
