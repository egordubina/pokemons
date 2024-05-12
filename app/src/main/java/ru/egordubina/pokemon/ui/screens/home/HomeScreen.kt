package ru.egordubina.pokemon.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import ru.egordubina.pokemon.R
import ru.egordubina.pokemon.ui.models.PokemonUiListItem
import ru.egordubina.pokemon.ui.theme.PokemonTheme
import ru.egordubina.pokemon.ui.theme.pokemonFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    pokemons: LazyPagingItems<PokemonUiListItem>,
    onPokemonClick: (Int) -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(stringResource(id = R.string.app_name), fontFamily = pokemonFontFamily) })
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->
        when (pokemons.loadState.refresh) {
            is LoadState.Loading -> if (pokemons.itemCount == 0) HomeLoading(innerPadding = innerPadding)
            is LoadState.Error -> HomeError(onRefreshButtonClick = { pokemons.retry() })
            is LoadState.NotLoading -> {}
        }
        if (pokemons.loadState.append is LoadState.Error)
            LaunchedEffect(key1 = snackBarHostState) {
                val result = snackBarHostState.showSnackbar(
                    message = context.resources.getString(R.string.pokemons_not_load),
                    actionLabel = context.resources.getString(R.string.retry)
                )
                if (result == SnackbarResult.ActionPerformed)
                    pokemons.retry()
            }
        HomeContent(
            pokemons = pokemons,
            onPokemonClick = onPokemonClick,
            innerPadding = innerPadding
        )
    }
}

@Composable
fun HomeError(onRefreshButtonClick: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        ) {
            Text(
                text = context.resources.getString(R.string.pokemons_not_load),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.pokemon_not_load),
            contentDescription = null,
            modifier = Modifier.clip(MaterialTheme.shapes.large)
        )
        Button(onClick = onRefreshButtonClick) {
            Text(text = context.resources.getString(R.string.retry))
        }
    }
}

@Preview
@Composable
private fun HomeErrorPreview() {
    PokemonTheme {
        HomeError {}
    }
}