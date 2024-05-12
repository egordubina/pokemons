package ru.egordubina.pokemon.ui.screens.detail

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.egordubina.pokemon.ui.theme.pokemonFontFamily

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonContent(uiState: PokemonUiState, innerPadding: PaddingValues) {
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
            modifier = Modifier.aspectRatio(1f),
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
                if (uiState.baseExperience != null)
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
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ) {

                        Text(
                            text = "${it.statName}: ${it.baseExp}",
                            fontFamily = pokemonFontFamily,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
    }
}