package ru.egordubina.pokemon.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.egordubina.pokemon.data.api.PokemonApiService
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonApiService: PokemonApiService,
) : ViewModel() {
    var uiState by mutableStateOf<HomeUiState>(HomeUiState.Content())
        private set

    init {
        uiState = HomeUiState.Loading
        viewModelScope.launch {
            try {
                val result = pokemonApiService.loadPokemons(100, 0)
                uiState = HomeUiState.Content(pokemons = result.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}