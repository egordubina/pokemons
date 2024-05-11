package ru.egordubina.pokemon.data.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.egordubina.pokemon.BuildConfig
import ru.egordubina.pokemon.data.api.PokemonsApiService
import ru.egordubina.pokemon.data.datasources.PokemonsPagingSource
import ru.egordubina.pokemon.data.models.PokemonItemApiResponse
import ru.egordubina.pokemon.data.repository.PokemonRepositoryImpl
import ru.egordubina.pokemon.domain.repository.PokemonRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonApiService(retrofit: Retrofit): PokemonsApiService =
        retrofit.create(PokemonsApiService::class.java)

    @Provides
    @Singleton
    fun providePokemonPager(pokemonsApiService: PokemonsApiService): Pager<Int, PokemonItemApiResponse> =
        Pager(
            config = PagingConfig(pageSize = 40, prefetchDistance = 20),
            pagingSourceFactory = { PokemonsPagingSource(pokemonsApiService = pokemonsApiService) }
        )

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokemonPager: Pager<Int, PokemonItemApiResponse>,
        pokemonsApiService: PokemonsApiService,
    ): PokemonRepository =
        PokemonRepositoryImpl(pokemonsPager = pokemonPager, pokemonsApiService = pokemonsApiService)
}