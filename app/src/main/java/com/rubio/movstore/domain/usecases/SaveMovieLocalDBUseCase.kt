package com.rubio.movstore.domain.usecases

import com.rubio.movstore.data.repository.MoviesRepository
import com.rubio.movstore.domain.entities.Movie
import javax.inject.Inject

class SaveMovieLocalDBUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(movies: List<Movie>?){
        moviesRepository.setMoviesToLocalDB(movies)
    }
}