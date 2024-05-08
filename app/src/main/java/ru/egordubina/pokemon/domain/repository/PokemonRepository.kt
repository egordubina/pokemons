package ru.egordubina.pokemon.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.egordubina.pokemon.data.models.PokemonDetailApiResponse
import ru.egordubina.pokemon.data.models.PokemonItemApiResponse

interface PokemonRepository {
    fun loadAllPokemons(): Flow<PagingData<PokemonItemApiResponse>>
    suspend fun loadPokemonDetailInfo(name: String): PokemonDetailApiResponse
}