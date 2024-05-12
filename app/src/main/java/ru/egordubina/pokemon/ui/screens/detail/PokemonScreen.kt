package ru.egordubina.pokemon.ui.screens.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ru.egordubina.pokemon.ui.screens.home.HomeError
import ru.egordubina.pokemon.ui.theme.PokemonTheme
import ru.egordubina.pokemon.ui.theme.pokemonFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(
    uiState: PokemonUiState,
    onBackButtonClick: () -> Unit,
    onRefreshButtonClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        uiState.name,
                        fontFamily = pokemonFontFamily,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when {
            uiState.isLoading -> PokemonLoading(innerPadding)
            uiState.isError -> HomeError(onRefreshButtonClick = onRefreshButtonClick)
            else -> PokemonContent(uiState, innerPadding)
        }
    }
}

@Preview
@Composable
private fun PokemonScreenPreview() {
    PokemonTheme {
        PokemonScreen(
            uiState = PokemonUiState(
                name = "bulbasaur",
                height = 100,
                weight = 567,
                baseExperience = 125,
                pokemonStats = listOf(
                    PokemonStat(baseExp = 100, effort = 1, statName = "HP"),
                    PokemonStat(baseExp = 100, effort = 1, statName = "HP"),
                    PokemonStat(baseExp = 100, effort = 1, statName = "HP"),
                    PokemonStat(baseExp = 100, effort = 1, statName = "HP"),
                    PokemonStat(baseExp = 100, effort = 1, statName = "HP"),
                    PokemonStat(baseExp = 100, effort = 1, statName = "HP"),
                )
            ), {}, {}
        )
    }
}