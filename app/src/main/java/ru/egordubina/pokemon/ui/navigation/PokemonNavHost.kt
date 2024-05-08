package ru.egordubina.pokemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.egordubina.pokemon.ui.screens.HomeScreen
import ru.egordubina.pokemon.ui.screens.HomeViewModel

@Composable
fun PokemonNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = AppDestinations.HOME.name) {
        composable(AppDestinations.HOME.name) {
            val vm = hiltViewModel<HomeViewModel>()
            val uiState = vm.uiState.collectAsState().value
            HomeScreen(uiState = uiState)
        }
    }
}