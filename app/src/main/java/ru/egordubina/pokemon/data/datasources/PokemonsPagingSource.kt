package ru.egordubina.pokemon.data.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.egordubina.pokemon.data.api.PokemonsApiService
import ru.egordubina.pokemon.data.models.PokemonItemApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonsPagingSource @Inject constructor(
    private val pokemonsApiService: PokemonsApiService,
) : PagingSource<Int, PokemonItemApiResponse>() {

    override val jumpingSupported: Boolean = true
    override fun getRefreshKey(state: PagingState<Int, PokemonItemApiResponse>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonItemApiResponse> {
        return try {
            val page = params.key ?: 0
            val response = pokemonsApiService.loadPokemons(
                limit = params.loadSize,
                offset = params.loadSize * page
            )
            val nextKey = if (response.results.isEmpty()) null else page.plus(1)
            val prevKey = if (page == 0) null else page.minus(1)
            val itemsBefore = page * params.loadSize
            val itemsAfter = response.count - (itemsBefore + response.results.size)
            LoadResult.Page(
                data = response.results,
                nextKey = nextKey,
                prevKey = prevKey,
                itemsAfter = itemsAfter,
                itemsBefore = itemsBefore
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}