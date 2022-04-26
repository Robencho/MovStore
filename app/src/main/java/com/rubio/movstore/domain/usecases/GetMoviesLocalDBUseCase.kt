package com.rubio.movstore.domain.usecases

import com.rubio.movstore.data.models.Movie
import com.rubio.movstore.data.repository.MoviesRepository
import javax.inject.Inject

class GetMoviesLocalDBUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(response: (data: (List<Movie>)) -> Unit){
        var responseListMovie = listOf<Movie>()
        moviesRepository.getMoviesFromLocalDB {
            responseListMovie = it
        }
        response(responseListMovie)
    }
}