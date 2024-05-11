package ru.egordubina.pokemon.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.egordubina.pokemon.domain.usecases.LoadPokemonsUseCase
import ru.egordubina.pokemon.ui.models.PokemonListItem
import ru.egordubina.pokemon.ui.models.asUiItem
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadPokemonsUseCase: LoadPokemonsUseCase,
) : ViewModel() {
    var pokemonsList: Flow<PagingData<PokemonListItem>> =
        loadPokemonsUseCase.loadPokemons().map { pagingData -> // todo посмотреть ещё раз
            pagingData.filter { it.id != null }.map { pokemon -> pokemon.asUiItem() }
        }.cachedIn(viewModelScope)
}