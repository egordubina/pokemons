package ru.egordubina.pokemon.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.egordubina.pokemon.domain.usecases.LoadPokemonsUseCase
import ru.egordubina.pokemon.ui.models.PokemonListItem
import ru.egordubina.pokemon.ui.models.asUiItem
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadPokemonsUseCase: LoadPokemonsUseCase,
) : ViewModel() {
    var pokemonsList: Flow<PagingData<PokemonListItem>> =
        loadPokemonsUseCase.loadPokemons().map { pagingData ->
            pagingData.map { pokemon -> pokemon.asUiItem() }
        }.cachedIn(viewModelScope)
}