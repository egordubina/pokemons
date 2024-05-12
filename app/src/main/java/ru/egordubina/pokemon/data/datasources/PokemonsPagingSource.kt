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
        val pageIndex = anchorPosition / PAGE_SIZE
        return pageIndex
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonItemApiResponse> {
        return try {
            val page = params.key ?: 0
            val response = pokemonsApiService.loadPokemons(
                limit = PAGE_SIZE,
                offset = PAGE_SIZE * page
            )
            val itemsBefore = page * PAGE_SIZE
            val itemsAfter = response.count - (itemsBefore + response.results.size)
            val nextKey = if (itemsAfter == 0) null else page.plus(1)
            val prevKey = if (page == 0) null else page.minus(1)
            LoadResult.Page(
                data = response.results,
                nextKey = nextKey,
                prevKey = prevKey,
                itemsAfter = itemsAfter,
                itemsBefore = itemsBefore
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}