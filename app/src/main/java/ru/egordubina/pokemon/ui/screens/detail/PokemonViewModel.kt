package ru.egordubina.pokemon.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.egordubina.pokemon.domain.models.Pokemon
import ru.egordubina.pokemon.domain.usecases.LoadPokemonsUseCase
import ru.egordubina.pokemon.ui.models.asUiItem
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loadPokemonsUseCase: LoadPokemonsUseCase,
) : ViewModel() {
    private val pokemonName = checkNotNull(savedStateHandle["pokemonName"]).toString()
    private var _uiState: MutableStateFlow<PokemonUiState> = MutableStateFlow(PokemonUiState())
    val uiState: StateFlow<PokemonUiState> = _uiState.asStateFlow()

    private var job: Job? = null

    init {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val pokemon = loadPokemonsUseCase.loadPokemonByName(pokemonName)
                _uiState.update {
                    it.copy(
                        name = pokemon.name,
                        image = pokemon.image,
                        baseExperience = pokemon.baseExperience,
                        height = pokemon.height,
                        weight = pokemon.weight,
                        pokemonStats = pokemon.pokemonStats
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isError = true) }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}