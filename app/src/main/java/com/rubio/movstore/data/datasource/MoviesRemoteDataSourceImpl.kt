package com.rubio.movstore.data.datasource

import com.rubio.movstore.data.datasource.remote.MoviesApi
import com.rubio.movstore.data.models.MovieResponse
import com.rubio.movstore.utils.MovStoreConstants
import retrofit2.Call
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val movieApi: MoviesApi
) : IMoviesRemoteDataSource {
    private var typeMovie: Call<MovieResponse>? = null

    override suspend fun getMovieStoreApi(
        category: String?,
        response: (data: Call<MovieResponse>?) -> Unit
    ): Call<MovieResponse>? {
        when (category) {
            MovStoreConstants.VALUE_MOVIE_POPULAR -> {
                typeMovie = movieApi.getPopularMovies(MovStoreConstants.KEY_MOVIE)
            }
        }
        response(typeMovie)
        return typeMovie
    }
}