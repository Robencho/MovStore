package com.rubio.movstore.domain.usecases

import com.rubio.movstore.data.models.Movie
import com.rubio.movstore.data.repository.MoviesRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend fun invoke(category: String?, response: ((data: List<Movie>?) -> Unit)) {
        moviesRepository.getMovies(category, response = {
            response(it)
        })
    }

    fun toFahrenheit(degree: Float): Float {
        return degree * 9 / 5 + 32
    }
}