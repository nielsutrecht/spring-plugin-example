package com.nibado.example.plugin.facade.movies

import java.time.LocalDate
import java.util.UUID

data class Movie(val id: String, val title: String, val released: LocalDate, val actors: List<Actor>)

data class Actor(val id: String, val name: String, val dateOfBirth: LocalDate)
