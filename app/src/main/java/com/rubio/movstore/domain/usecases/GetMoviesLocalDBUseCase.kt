package com.rubio.movstore.domain.usecases

import com.rubio.movstore.data.repository.MoviesRepository
import com.rubio.movstore.domain.entities.Movie
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