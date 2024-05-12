package ru.egordubina.pokemon.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.egordubina.pokemon.domain.models.Pokemon
import ru.egordubina.pokemon.ui.screens.detail.PokemonStat

/**
 * @see <a href="https://pokeapi.co/docs/v2">Full API Doc</a>
 * @see <a href="https://pokeapi.co/docs/v2#pokemon">Current API Doc</a>
 * */
@Serializable
data class PokemonDetailApiResponse(
    @SerialName("base_experience") val baseExperience: Int?, // Опыт
    @SerialName("height") val height: Int, // Высота
    @SerialName("id") val id: Int, // id
    @SerialName("name") val name: String, // Имя
    @SerialName("sprites") val sprites: Sprites, // Спрайты покемона
    @SerialName("stats") val stats: List<PokemonStatApiResponse>, // Базовые статистики покемона
    @SerialName("weight") val weight: Int, // Вес покемона
)

@Serializable
data class Sprites(
    @SerialName("back_default") val backDefault: String?,
    @SerialName("back_female") val backFemale: String?,
    @SerialName("back_shiny") val backShiny: String?,
    @SerialName("back_shiny_female") val backShinyFemale: String?,
    @SerialName("front_default") val frontDefault: String?,
    @SerialName("front_female") val frontFemale: String?,
    @SerialName("front_shiny") val frontShiny: String?,
    @SerialName("front_shiny_female") val frontShinyFemale: String?,
)

@Serializable
data class PokemonStatApiResponse(
    val base_stat: Int?,
    val effort: Int?,
    val stat: Stat?,
)

@Serializable
data class Stat(
    val name: String?,
    val url: String?,
)

fun PokemonDetailApiResponse.asDomain(): Pokemon = Pokemon(
    id = this.id,
    images = listOf(
        this.sprites.backDefault ?: "",
        this.sprites.backFemale ?: "",
        this.sprites.backShiny ?: "",
        this.sprites.backShinyFemale ?: "",
        this.sprites.frontDefault ?: "",
        this.sprites.frontFemale ?: "",
        this.sprites.frontShiny ?: "",
        this.sprites.frontShinyFemale ?: "",
    ),
    name = this.name,
    baseExperience = this.baseExperience,
    height = this.height,
    weight = this.weight,
    pokemonStats = this.stats.map { it.asDomain() }
)

fun PokemonStatApiResponse.asDomain(): PokemonStat = PokemonStat(
    baseExp = this.base_stat,
    effort = this.effort,
    statName = this.stat?.name,
)
