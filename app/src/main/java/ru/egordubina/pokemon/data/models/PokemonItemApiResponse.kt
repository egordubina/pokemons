package ru.egordubina.pokemon.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonItemApiResponse(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)
