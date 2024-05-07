package ru.egordubina.pokemon.ui.screens

import ru.egordubina.pokemon.data.models.PokemonItemApiResponse

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data object Error : HomeUiState
    data class Content(
        val pokemons: List<PokemonItemApiResponse> = emptyList()
    ) : HomeUiState
}