package com.rubio.movstore.data.di

import com.rubio.movstore.data.apiservice.MoviesApi
import com.rubio.movstore.utils.MovStoreConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(MovStoreConstants.API_MOVIE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideMovieApi(retrofit: Retrofit):MoviesApi = retrofit.create(MoviesApi::class.java)
}