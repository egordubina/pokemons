package ru.egordubina.pokemon.ui.screens.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
        modifier = modifier
            .consumeWindowInsets(innerPadding)
            .fillMaxSize()
    ) {
        items(10) {
            CardSkeleton()
        }
    }
}

@Composable
fun CardSkeleton() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    Card(modifier = Modifier.fillMaxWidth().alpha(alpha)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(100))
                    .background(MaterialTheme.colorScheme.surfaceBright)
            )
            Column(
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 4.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(MaterialTheme.colorScheme.surfaceBright)
                )
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .width(96.dp)
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
private fun HomeLoadingPreview() {
    PokemonTheme {
        HomeLoading(innerPadding = PaddingValues())
    }
}