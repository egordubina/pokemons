package ru.egordubina.pokemon.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.egordubina.pokemon.domain.usecases.LoadPokemonsUseCase
import ru.egordubina.pokemon.ui.models.PokemonListItem
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadPokemonsUseCase: LoadPokemonsUseCase,
) : ViewModel() {
    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    private var job: Job? = null

    init {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                val pokemons = loadPokemonsUseCase.loadPokemons().map { pagingData ->
                    pagingData.map { pokemon ->
                        PokemonListItem(
                            id = pokemon.id,
                            image = pokemon.image,
                            name = pokemon.name,
                            baseExperience = pokemon.baseExperience,
                            height = pokemon.height,
                            weight = pokemon.weight
                        )
                    }
                }.cachedIn(viewModelScope)
                _uiState.update { it.copy(pokemons = pokemons) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isError = true) }
            }
        }
    }
}