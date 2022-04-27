package com.rubio.movstore.data.repository

import com.rubio.movstore.data.datasource.MoviesRemoteDataSourceImpl
import com.rubio.movstore.data.datasource.local.moviedao.MovieStoreDao
import com.rubio.movstore.data.models.*
import com.rubio.movstore.domain.entities.Movie
import com.rubio.movstore.domain.entities.MoviesResponse
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
           it?.enqueue(object : Callback<MovieResponseParcelable>{
                override fun onResponse(
                    call: Call<MovieResponseParcelable>,
                    response: Response<MovieResponseParcelable>
                ) {
                    response(response.body()?.toMovieResponseEntity()?.results)
                }
                override fun onFailure(call: Call<MovieResponseParcelable>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        })
    }

    suspend fun setMoviesToLocalDB(movies: List<Movie>?) {
        val listMoviesParcelable = mutableListOf<MovieParcelable>()
        movies?.forEach { listMoviesParcelable.add(it.toMovieParcelable()) }
        movieDao.updateMoviesTable(listMoviesParcelable)
    }

    suspend fun getMoviesFromLocalDB(response: (data: List<Movie>) -> Unit) {
        val listMovies = mutableListOf<Movie>()
        movieDao.getAllMoviesFromLocal().forEach { listMovies.add(it.toMovieEntity()) }
        response(listMovies)
    }

}