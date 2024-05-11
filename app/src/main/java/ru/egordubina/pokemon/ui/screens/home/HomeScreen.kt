package ru.egordubina.pokemon.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.launch
import ru.egordubina.pokemon.R
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
        when (pokemons.loadState.refresh) {
            is LoadState.Loading -> HomeLoading(innerPadding = innerPadding)
            is LoadState.Error -> HomeError(onRefreshButtonClick = { pokemons.retry() })
            is LoadState.NotLoading -> {}
        }
        if (pokemons.loadState.append is LoadState.Error)
            LaunchedEffect(key1 = snackBarHostState) {
                val result = snackBarHostState.showSnackbar(
                    message = "Покемоны не загрузились",
                    actionLabel = "Повторить"
                )
                if (result == SnackbarResult.ActionPerformed)
                    pokemons.retry()
            }
        HomeContent(
            pokemons = pokemons,
            innerPadding = innerPadding
        )
    }
}

@Composable
fun HomeError(onRefreshButtonClick: () -> Unit) {
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
                text = "Покемоны не загрузились",
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
            Text(text = "Попробовать ещё раз")
        }
    }
}
