package com.rubio.movstore.data.apiservice

import com.rubio.movstore.data.models.MovieResponse
import com.rubio.movstore.utils.MovStoreConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET(MovStoreConstants.EP_MOVIE_POPULAR)
    fun getPopularMovies(@Query("api_key") userKey: String): retrofit2.Call<MovieResponse>
}