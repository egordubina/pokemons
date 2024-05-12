package ru.egordubina.pokemon.ui.screens.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.egordubina.pokemon.ui.theme.pokemonFontFamily

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonContent(uiState: PokemonUiState, innerPadding: PaddingValues) {
    val pagerState = rememberPagerState(pageCount = { uiState.images.size })
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
        HorizontalPager(state = pagerState) {
            AsyncImage(
                model = uiState.images[it],
                contentDescription = null,
                modifier = Modifier.aspectRatio(1f),
            )
        }
        Text(
            text = uiState.name,
            fontFamily = pokemonFontFamily,
            fontSize = 48.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "About", fontFamily = pokemonFontFamily, fontSize = 32.sp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                PokemonStatCard(text = "Height", value = uiState.height.toString())
                PokemonStatCard(text = "Weight", value = uiState.weight.toString())
                if (uiState.baseExperience != null)
                    PokemonStatCard(text = "Base exp", value = uiState.baseExperience.toString())
            }
        }
        PokemonStats(uiState.pokemonStats)
        Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PokemonStats(pokemonStats: List<PokemonStat>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Stats", fontFamily = pokemonFontFamily, fontSize = 32.sp)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            pokemonStats.forEach {
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
}

@Composable
private fun RowScope.PokemonStatCard(text: String, value: String) {
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
            Text(text = text, fontFamily = pokemonFontFamily)
            Text(
                text = value,
                fontFamily = pokemonFontFamily,
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 32.sp
            )
        }
    }
}