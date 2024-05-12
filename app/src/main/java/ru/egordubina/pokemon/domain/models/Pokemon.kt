package ru.egordubina.pokemon.domain.models

import ru.egordubina.pokemon.ui.screens.detail.PokemonStat

data class Pokemon(
    val id: Int,
    val images: List<String>,
    val name: String,
    val baseExperience: Int?,
    val weight: Int,
    val height: Int,
    val pokemonStats: List<PokemonStat>
)

