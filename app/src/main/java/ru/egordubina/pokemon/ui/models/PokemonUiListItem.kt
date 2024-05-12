package ru.egordubina.pokemon.ui.models

import ru.egordubina.pokemon.domain.models.Pokemon

data class PokemonUiListItem(
    val id: Int,
    val image: String,
    val name: String,
    val baseExperience: Int?,
    val height: Int,
    val weight: Int,
)

fun Pokemon.asUiItem(): PokemonUiListItem = PokemonUiListItem(
    id = this.id,
    image = this.images[4].ifEmpty { this.images[0].ifEmpty { this.images[5].ifEmpty { this.images[1] } } },
    name = this.name,
    baseExperience = this.baseExperience,
    height = this.height,
    weight = this.weight,
)
