package com.nibado.example.plugin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PluginExampleApplication

fun main(args: Array<String>) {
	runApplication<PluginExampleApplication>(*args)
}
