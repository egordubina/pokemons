package ru.egordubina.pokemon.ui.screens

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ru.egordubina.pokemon.data.models.PokemonItemApiResponse
import ru.egordubina.pokemon.ui.models.PokemonListItem

data class HomeUiState(
//    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val pokemons: Flow<PagingData<PokemonListItem>> = emptyFlow(),
)