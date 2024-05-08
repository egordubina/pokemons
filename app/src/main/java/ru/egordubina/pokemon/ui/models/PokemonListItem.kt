package ru.egordubina.pokemon.ui.models

data class PokemonListItem(
    val id: Int,
    val url: String,
    val image: String,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val weight: Int,
//    id = pokemonDetailInfo.id,
//    url = it.url,
//    image = pokemonDetailInfo.sprites.frontDefault ?: "",
//    name = it.name,
//    baseExperience = pokemonDetailInfo.baseExperience,
//    height = pokemonDetailInfo.height,
//    weight = pokemonDetailInfo.weight
)