package ru.egordubina.pokemon.domain.models

data class Pokemon(
    val id: Int,
    val image: String,
    val name: String,
    val baseExperience: Int,
    val weight: Int,
    val height: Int,
)
