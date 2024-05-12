package ru.egordubina.pokemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import ru.egordubina.pokemon.ui.screens.detail.PokemonScreen
import ru.egordubina.pokemon.ui.screens.detail.PokemonViewModel
import ru.egordubina.pokemon.ui.screens.home.HomeScreen
import ru.egordubina.pokemon.ui.screens.home.HomeViewModel

@Composable
fun PokemonNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = AppDestinations.HOME.name) {
        composable(AppDestinations.HOME.name) {
            val vm = hiltViewModel<HomeViewModel>()
            val pokemons = vm.pokemonsList.collectAsLazyPagingItems()
            HomeScreen(
                pokemons = pokemons,
                onPokemonClick = { navController.navigate("${AppDestinations.DETAIL.name}/$it") }
            )
        }
        composable(
            "${AppDestinations.DETAIL.name}/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) {
            val vm = hiltViewModel<PokemonViewModel>()
            val uiState = vm.uiState.collectAsState().value
            PokemonScreen(uiState = uiState, onBackButtonClick = { navController.navigateUp() })
        }
    }
}