package ru.egordubina.pokemon.domain.usecases

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.egordubina.pokemon.data.models.asDomain
import ru.egordubina.pokemon.domain.models.Pokemon
import ru.egordubina.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class LoadPokemonsUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : LoadPokemonsUseCase {
    override val pokemons: Flow<PagingData<Pokemon>> =
        pokemonRepository.loadAllPokemons().map { pagingData ->
            try {
                pagingData.map { pokemonRepository.loadPokemonDetailInfo(it.name).asDomain() }
            } catch (e: Exception) {
                PagingData.empty()
            }
        }

    override suspend fun loadPokemonByName(name: String): Pokemon =
        pokemonRepository.loadPokemonDetailInfo(name = name).asDomain()
}