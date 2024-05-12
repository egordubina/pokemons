package ru.egordubina.pokemon.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.egordubina.pokemon.domain.usecases.LoadPokemonsUseCase
import ru.egordubina.pokemon.ui.models.PokemonUiListItem
import ru.egordubina.pokemon.ui.models.asUiItem
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadPokemonsUseCase: LoadPokemonsUseCase,
) : ViewModel() {
    var pokemonsList: Flow<PagingData<PokemonUiListItem>> =
        loadPokemonsUseCase.pokemons.map { pagingData ->
            pagingData.map { pokemon -> pokemon.asUiItem() }
        }.cachedIn(viewModelScope).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )
}