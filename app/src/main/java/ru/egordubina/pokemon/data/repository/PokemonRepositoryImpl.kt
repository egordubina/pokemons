package ru.egordubina.pokemon.data.repository

import ru.egordubina.pokemon.data.api.PokemonApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApiService: PokemonApiService
) : PokemonRepository {
    override fun loadAllPokemons() {

    }
}