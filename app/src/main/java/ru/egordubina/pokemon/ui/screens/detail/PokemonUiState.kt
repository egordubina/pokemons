package ru.egordubina.pokemon.ui.screens.detail

data class PokemonUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val name: String = "",
    val images: List<String> = emptyList(),
    val baseExperience: Int? = null,
    val height: Int = 0,
    val weight: Int = 0,
    val pokemonStats: List<PokemonStat> = emptyList(),
)

data class PokemonStat(
    val baseExp: Int?,
    val effort: Int?,
    val statName: String?,
)
