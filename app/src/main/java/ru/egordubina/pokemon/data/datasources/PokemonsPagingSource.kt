package ru.egordubina.pokemon.data.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.egordubina.pokemon.data.api.PokemonsApiService
import ru.egordubina.pokemon.data.models.PokemonItemApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonsPagingSource @Inject constructor(
    private val pokemonsApiService: PokemonsApiService
) : PagingSource<Int, PokemonItemApiResponse>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonItemApiResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonItemApiResponse> {
        return try {
            val page = params.key ?: 0
            val limit = 20
            val response = pokemonsApiService.loadPokemons(limit = limit, offset = limit * page)
            val nextKey = if (response.results.isEmpty()) null else page.plus(1)
            val prevKey = if (page == 0) null else page.minus(1)
            LoadResult.Page(
                data = response.results,
                nextKey = nextKey,
                prevKey = prevKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}