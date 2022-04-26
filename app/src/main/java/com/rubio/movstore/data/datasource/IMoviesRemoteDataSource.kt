package com.rubio.movstore.data.datasource

import com.rubio.movstore.data.models.MovieResponse
import retrofit2.Call

interface IMoviesRemoteDataSource {
    suspend fun getMovieStoreApi(
        category: String?,
        response: (data: Call<MovieResponse>?) -> Unit
    ):Call<MovieResponse>?
}