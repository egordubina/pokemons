package ru.egordubina.pokemon.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import ru.egordubina.pokemon.BuildConfig
import ru.egordubina.pokemon.data.api.PokemonApiService

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRetrofitClient(): Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BuildConfig.BASE_URL)
        .build()

    @Provides
    fun providePokemonApiService(retrofit: Retrofit): PokemonApiService =
        retrofit.create(PokemonApiService::class.java)
}