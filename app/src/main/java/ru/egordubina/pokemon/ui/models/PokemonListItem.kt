package ru.egordubina.pokemon.ui.models

import ru.egordubina.pokemon.domain.models.Pokemon

data class PokemonListItem(
    val id: Int,
    val image: String,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val weight: Int,
)

fun Pokemon.asUiItem(): PokemonListItem = PokemonListItem(
    id = this.id,
    image = this.image,
    name = this.name,
    baseExperience = this.baseExperience,
    height = this.height,
    weight = this.weight,
)

fun List<Pokemon>.asUiItemsList(): List<PokemonListItem> = this.map { pokemon -> pokemon.asUiItem() }