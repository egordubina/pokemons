package ru.egordubina.pokemon.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.egordubina.pokemon.data.models.PokemonsApiResponse

interface PokemonApiService {
    @GET("pokemon")
    suspend fun loadPokemons(@Query("limit") limit: Int, @Query("offset") offset: Int): PokemonsApiResponse

    @GET("pokemon/{id}")
    suspend fun loadPokemonDetailInfo(@Path("id") id: Int): String
}