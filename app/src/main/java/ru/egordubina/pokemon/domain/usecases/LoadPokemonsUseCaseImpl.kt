package ru.egordubina.pokemon.domain.usecases

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import ru.egordubina.pokemon.data.models.PokemonDetailApiResponse
import ru.egordubina.pokemon.domain.models.Pokemon
import ru.egordubina.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class LoadPokemonsUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : LoadPokemonsUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun loadPokemons(): Flow<PagingData<Pokemon>> {
        val pokemons = pokemonRepository.loadAllPokemons()
        return pokemons.flatMapLatest { pagingData ->
            val names = pagingData.map { it.name }
            val pokemonDetails = loadPokemonDetails(names)
            val pokemonList = pokemonDetails.map { pokemonDetailInfo ->
                Pokemon(
                    id = pokemonDetailInfo.id,
                    image = pokemonDetailInfo.sprites.frontDefault ?: "",
                    name = pokemonDetailInfo.name,
                    baseExperience = pokemonDetailInfo.baseExperience,
                    height = pokemonDetailInfo.height,
                    weight = pokemonDetailInfo.weight
                )
            }
            flowOf(pokemonList)
        }
    }

    private suspend fun loadPokemonDetails(names: PagingData<String>): PagingData<PokemonDetailApiResponse> {
        val answers = names.map { name ->
            withContext(Dispatchers.IO) {
                async { pokemonRepository.loadPokemonDetailInfo(name) }
            }
        }
        return answers.map { it.await() }
    }
}