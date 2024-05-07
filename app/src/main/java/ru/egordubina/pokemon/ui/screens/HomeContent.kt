package ru.egordubina.pokemon.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.egordubina.pokemon.data.models.PokemonItemApiResponse

@Composable
fun HomeContent(
    pokemons: List<PokemonItemApiResponse>,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
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
        items(pokemons, key = { it.name }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(it.name)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
        }
    }
}