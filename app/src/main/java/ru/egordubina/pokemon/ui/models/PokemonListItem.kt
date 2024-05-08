package ru.egordubina.pokemon.ui.models

data class PokemonListItem(
    val id: Int,
    val image: String,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val weight: Int,
)