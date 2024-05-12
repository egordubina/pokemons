package ru.egordubina.pokemon.ui.screens.detail

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.egordubina.pokemon.ui.theme.PokemonTheme
import ru.egordubina.pokemon.ui.theme.pokemonFontFamily

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PokemonScreen(uiState: PokemonUiState, onBackButtonClick: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(uiState.name, fontFamily = pokemonFontFamily) },
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
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .padding(
                    PaddingValues(
                        top = innerPadding.calculateTopPadding(),
                        start = 16.dp,
                        end = 16.dp
                    )
                )
                .verticalScroll(rememberScrollState())
                .consumeWindowInsets(innerPadding)
        ) {
            AsyncImage(
                model = uiState.image,
                contentDescription = null,
                modifier = Modifier.aspectRatio(1f)
            )
            Text(
                text = uiState.name,
                fontFamily = pokemonFontFamily,
                fontSize = 48.sp
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "About", fontFamily = pokemonFontFamily, fontSize = 32.sp)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.33f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = "Height", fontFamily = pokemonFontFamily)
                            Text(
                                text = "${uiState.height}",
                                fontFamily = pokemonFontFamily,
                                style = MaterialTheme.typography.headlineLarge,
                                fontSize = 32.sp
                            )
                        }
                    }
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.33f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = "Weight", fontFamily = pokemonFontFamily)
                            Text(
                                text = "${uiState.weight}",
                                fontFamily = pokemonFontFamily,
                                style = MaterialTheme.typography.headlineLarge,
                                fontSize = 32.sp
                            )
                        }
                    }
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.33f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = "Base exp", fontFamily = pokemonFontFamily)
                            Text(
                                text = "${uiState.baseExperience}",
                                fontFamily = pokemonFontFamily,
                                style = MaterialTheme.typography.headlineLarge,
                                fontSize = 32.sp
                            )
                        }
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Stats", fontFamily = pokemonFontFamily, fontSize = 32.sp)
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    uiState.pokemonStats.forEach {
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ) {
                            Text(
                                text = "${it.statName}: ${it.baseExp}",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
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
            ), {}
        )
    }
}