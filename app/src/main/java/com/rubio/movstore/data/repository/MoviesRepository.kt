package com.rubio.movstore.data.repository

import com.rubio.movstore.data.datasource.MoviesRemoteDataSourceImpl
import com.rubio.movstore.data.datasource.local.moviedao.MovieStoreDao
import com.rubio.movstore.data.models.Movie
import com.rubio.movstore.data.models.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSourceImpl: MoviesRemoteDataSourceImpl,
    private val movieDao: MovieStoreDao
) : IMoviesRepository {

    override suspend fun getMovies(
        category: String?,
        response: (data: List<Movie>?) -> Unit
    ) {
        moviesRemoteDataSourceImpl.getMovieStoreApi(category, response = {
           it?.enqueue(object : Callback<MovieResponse>{
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    response(response.body()?.results)
                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        })
    }

    suspend fun setMoviesToLocalDB(movies: List<Movie>?) {
        movieDao.updateMoviesTable(movies)
    }

    suspend fun getMoviesFromLocalDB(response: (data: List<Movie>) -> Unit) {
        response(movieDao.getAllMoviesFromLocal())
    }

}