package ru.egordubina.pokemon.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.egordubina.pokemon.ui.models.PokemonUiListItem
import ru.egordubina.pokemon.ui.theme.PokemonTheme
import ru.egordubina.pokemon.ui.theme.pokemonFontFamily

@Composable
fun HomeContent(
    pokemons: LazyPagingItems<PokemonUiListItem>,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    onPokemonClick: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
            top = innerPadding.calculateTopPadding(),
            bottom = innerPadding.calculateBottomPadding()
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.consumeWindowInsets(innerPadding)
    ) {
        items(
            count = pokemons.itemCount,
            key = pokemons.itemKey { it.id },
            contentType = pokemons.itemContentType { "Pokemons " },
        ) {
            val item = pokemons[it]
            if (item != null)
                PokemonCard(item, onPokemonClick)
            else
                CardSkeleton()
        }
        item {
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
        }
    }
}

@Composable
private fun PokemonCard(pokemon: PokemonUiListItem, onPokemonClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .clickable { onPokemonClick(pokemon.id) }
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(96.dp),
            )
            Column {
                Text(
                    pokemon.name,
                    fontFamily = pokemonFontFamily,
                    style = MaterialTheme.typography.headlineSmall
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (pokemon.baseExperience != null)
                        PokemonStat("Base exp: ${pokemon.baseExperience}")
                    PokemonStat("Weight: ${pokemon.weight}")
                    PokemonStat("Height: ${pokemon.height}")
                }
            }
        }
    }
}

@Composable
private fun PokemonStat(text: String) {
    Text(
        text,
        fontFamily = pokemonFontFamily,
        style = MaterialTheme.typography.labelLarge
    )
}

@Preview
@Composable
private fun PokemonCardPreview() {
    PokemonTheme {
        PokemonCard(
            pokemon = PokemonUiListItem(
                id = 0,
                image = "",
                name = "Bulbasaur",
                baseExperience = 100,
                height = 10,
                weight = 100
            ),
            onPokemonClick = {}
        )
    }
}