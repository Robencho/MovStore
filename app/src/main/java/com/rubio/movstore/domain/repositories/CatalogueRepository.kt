package com.rubio.movstore.domain.repositories

import com.rubio.movstore.data.apiservice.MoviesApi
import com.rubio.movstore.data.models.MovieResponse
import com.rubio.movstore.utils.MovStoreConstants
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogueRepository @Inject constructor(private val movieStoreApi:MoviesApi) {
    private var typeMovie: Call<MovieResponse>? = null

    fun getMovStore(category:String?, response: (data:Call<MovieResponse>?)->Unit):Call<MovieResponse>?{
        when(category){
            MovStoreConstants.VALUE_MOVIE_POPULAR->{
                typeMovie = movieStoreApi.getPopularMovies(MovStoreConstants.KEY_MOVIE)
            }
            MovStoreConstants.VALUE_MOVIE_LATEST->{
                //typeMovie = client?.getLatestMovies(MovStoreConstants.KEY_MOVIE)
            }
            MovStoreConstants.VALUE_MOVIE_TOP_RATED->{
               // typeMovie = client?.getTopRatedMovies(MovStoreConstants.KEY_MOVIE)
            }
            MovStoreConstants.VALUE_MOVIE_UPCOMING->{
                //typeMovie = client?.getUpComingMovies(MovStoreConstants.KEY_MOVIE)
            }
        }
        response(typeMovie)
        return typeMovie
    }
}