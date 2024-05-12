package ru.egordubina.pokemon.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.egordubina.pokemon.domain.models.Pokemon

interface LoadPokemonsUseCase {
    val pokemons: Flow<PagingData<Pokemon>>
    suspend fun loadPokemonByName(name: String): Pokemon
}