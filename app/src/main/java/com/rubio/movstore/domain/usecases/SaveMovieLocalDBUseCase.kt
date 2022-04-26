package com.rubio.movstore.domain.usecases

import com.rubio.movstore.data.models.Movie
import com.rubio.movstore.data.repository.MoviesRepository
import javax.inject.Inject

class SaveMovieLocalDBUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(movs: List<Movie>?){
        moviesRepository.setMoviesToLocalDB(movs)
    }
}