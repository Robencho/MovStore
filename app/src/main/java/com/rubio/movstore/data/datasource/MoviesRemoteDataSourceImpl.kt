package com.rubio.movstore.data.datasource

import com.rubio.movstore.data.datasource.remote.MoviesApi
import com.rubio.movstore.data.models.MovieResponseParcelable
import com.rubio.movstore.utils.MovStoreConstants
import retrofit2.Call
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val movieApi: MoviesApi
) : IMoviesRemoteDataSource {
    private var typeMovie: Call<MovieResponseParcelable>? = null

    override suspend fun getMovieStoreApi(
        category: String?,
        response: (data: Call<MovieResponseParcelable>?) -> Unit
    ): Call<MovieResponseParcelable>? {
        when (category) {
            MovStoreConstants.VALUE_MOVIE_POPULAR -> {
                typeMovie = movieApi.getPopularMovies(MovStoreConstants.KEY_MOVIE)
            }
            MovStoreConstants.VALUE_MOVIE_LATEST -> {
                typeMovie = movieApi.getLatestMovies(MovStoreConstants.KEY_MOVIE)
            }
            MovStoreConstants.VALUE_MOVIE_TOP_RATED -> {
                typeMovie = movieApi.getTopRatedMovies(MovStoreConstants.KEY_MOVIE)
            }
            MovStoreConstants.VALUE_MOVIE_UPCOMING ->{
                typeMovie = movieApi.getUpComingMovies(MovStoreConstants.KEY_MOVIE)
            }
        }
        response(typeMovie)
        return typeMovie
    }
}