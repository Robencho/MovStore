package com.rubio.movstore.data.datasource

import com.rubio.movstore.data.models.MovieResponseParcelable
import retrofit2.Call

interface IMoviesRemoteDataSource {
    suspend fun getMovieStoreApi(
        category: String?,
        response: (data: Call<MovieResponseParcelable>?) -> Unit
    ):Call<MovieResponseParcelable>?
}