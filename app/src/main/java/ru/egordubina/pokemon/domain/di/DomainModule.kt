package ru.egordubina.pokemon.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.egordubina.pokemon.domain.repository.PokemonRepository
import ru.egordubina.pokemon.domain.usecases.LoadPokemonsUseCase
import ru.egordubina.pokemon.domain.usecases.LoadPokemonsUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    fun provideLoadPokemonsUseCase(pokemonRepository: PokemonRepository): LoadPokemonsUseCase =
        LoadPokemonsUseCaseImpl(pokemonRepository = pokemonRepository)
}