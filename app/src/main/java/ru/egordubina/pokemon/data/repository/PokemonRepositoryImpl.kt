package ru.egordubina.pokemon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.egordubina.pokemon.data.api.PokemonsApiService
import ru.egordubina.pokemon.data.models.PokemonDetailApiResponse
import ru.egordubina.pokemon.data.models.PokemonItemApiResponse
import ru.egordubina.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonsPager: Pager<Int, PokemonItemApiResponse>,
    private val pokemonsApiService: PokemonsApiService,
) : PokemonRepository {
    override fun loadAllPokemons(): Flow<PagingData<PokemonItemApiResponse>> = pokemonsPager.flow
    override suspend fun loadPokemonDetailInfo(name: String): PokemonDetailApiResponse = pokemonsApiService.loadPokemonDetail(name = name)
}