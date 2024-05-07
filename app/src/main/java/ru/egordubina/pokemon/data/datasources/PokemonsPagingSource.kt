package ru.egordubina.pokemon.data.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.egordubina.pokemon.data.models.PokemonsApiResponse

class PokemonsPagingSource : PagingSource<Int, PokemonsApiResponse>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonsApiResponse>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonsApiResponse> {
        TODO()
//        val start = params.key ?: 0
//        val range = start.until(start + params.loadSize)
//        return LoadResult.Page(
//            data = range.map { number ->
//                PokemonsApiResponse()
//            }
//        )
    }
}