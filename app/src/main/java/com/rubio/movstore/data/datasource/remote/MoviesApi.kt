package com.rubio.movstore.data.datasource.remote

import com.rubio.movstore.data.models.MovieResponseParcelable
import com.rubio.movstore.utils.MovStoreConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET(MovStoreConstants.EP_MOVIE_POPULAR)
    fun getPopularMovies(@Query("api_key") userKey: String): retrofit2.Call<MovieResponseParcelable>

    @GET(MovStoreConstants.EP_MOVIE_LATEST)
    fun getLatestMovies(@Query("api_key") userKey: String): retrofit2.Call<MovieResponseParcelable>

    @GET(MovStoreConstants.EP_MOVIE_TOP_RATED)
    fun getTopRatedMovies(@Query("api_key") userKey: String): retrofit2.Call<MovieResponseParcelable>

    @GET(MovStoreConstants.EP_MOVIE_UPCOMING)
    fun getUpComingMovies(@Query("api_key") userKey: String): retrofit2.Call<MovieResponseParcelable>

}