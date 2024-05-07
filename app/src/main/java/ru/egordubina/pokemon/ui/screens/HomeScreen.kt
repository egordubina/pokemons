package ru.egordubina.pokemon.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(uiState: HomeUiState) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Покемоны") })
        }
    ) { innerPadding ->
        AnimatedContent(uiState, label = "") {
            when (it) {
                is HomeUiState.Content -> HomeContent(
                    pokemons = it.pokemons,
                    innerPadding = innerPadding
                )

                HomeUiState.Error -> HomeError()
                HomeUiState.Loading -> HomeLoading(innerPadding = innerPadding)
            }
        }
    }
}