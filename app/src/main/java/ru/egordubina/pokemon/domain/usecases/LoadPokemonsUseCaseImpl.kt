package ru.egordubina.pokemon.domain.usecases

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import ru.egordubina.pokemon.domain.models.Pokemon
import ru.egordubina.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject
import kotlin.random.Random

class LoadPokemonsUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : LoadPokemonsUseCase {
    override fun loadPokemons(): Flow<PagingData<Pokemon>> =
        pokemonRepository.loadAllPokemons().map { pagingData ->
            pagingData.map {
                try {
                    val pokemonDetailInfo = pokemonRepository.loadPokemonDetailInfo(it.name)
                    Pokemon(
                        id = pokemonDetailInfo.id,
                        image = pokemonDetailInfo.sprites.frontDefault ?: "",
                        name = it.name,
                        baseExperience = pokemonDetailInfo.baseExperience,
                        height = pokemonDetailInfo.height,
                        weight = pokemonDetailInfo.weight
                    )
                } catch (e: Exception) {
                    Pokemon()
                }
            }
        }
}