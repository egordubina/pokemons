package ru.egordubina.pokemon.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.egordubina.pokemon.ui.theme.PokemonTheme

@Composable
fun HomeLoading(innerPadding: PaddingValues, modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
            top = innerPadding.calculateTopPadding(),
            bottom = innerPadding.calculateBottomPadding()
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.consumeWindowInsets(innerPadding).fillMaxSize()
    ) {
        items(10) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier
                    .height(32.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(top = 8.dp, bottom = 4.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.surfaceBright)
                )
                Box(modifier = Modifier
                    .height(32.dp)
                    .width(96.dp)
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp, top = 4.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.surfaceBright)
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeLoadingPreview() {
    PokemonTheme {
        HomeLoading(innerPadding = PaddingValues())
    }
}