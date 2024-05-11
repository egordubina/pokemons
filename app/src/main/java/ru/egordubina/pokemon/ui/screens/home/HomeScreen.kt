package ru.egordubina.pokemon.ui.screens.home

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.launch
import ru.egordubina.pokemon.ui.models.PokemonListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(pokemons: LazyPagingItems<PokemonListItem>) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Покемоны") })
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->
        if (pokemons.loadState.refresh is LoadState.Loading)
            HomeLoading(innerPadding = innerPadding)
        HomeContent(
            pokemons = pokemons,
            innerPadding = innerPadding,
            onDontLoadPokemons = {
                scope.launch {
                    refreshPokemons(snackBarHostState, pokemons)
                }
            }
        )
    }
}

private suspend fun refreshPokemons(
    snackBarHostState: SnackbarHostState,
    pokemons: LazyPagingItems<PokemonListItem>
) {
    val result = snackBarHostState.showSnackbar(
        "Покемоны не загрузились",
        actionLabel = "Повторить",
        withDismissAction = false
    )
    if (result == SnackbarResult.ActionPerformed)
        pokemons.retry()
}