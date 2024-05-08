package ru.egordubina.pokemon.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.egordubina.pokemon.data.models.PokemonItemApiResponse
import ru.egordubina.pokemon.domain.models.Pokemon

interface LoadPokemonsUseCase {
    suspend fun loadPokemons(): Flow<PagingData<Pokemon>>
}