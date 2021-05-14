package com.rubio.movstore.data.di

import android.content.Context
import androidx.room.Room
import com.rubio.movstore.data.apiservice.MoviesApi
import com.rubio.movstore.data.db.LocalDataBaseInject
import com.rubio.movstore.data.db.moviedao.MovieStoreDao
import com.rubio.movstore.utils.MovStoreConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    fun provideChannelDao(appDatabase: LocalDataBaseInject): MovieStoreDao {
        return appDatabase.movieStoreDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): LocalDataBaseInject {
        return Room.databaseBuilder(
            appContext,
            LocalDataBaseInject::class.java,
            "RssReader"
        ).build()
    }
}