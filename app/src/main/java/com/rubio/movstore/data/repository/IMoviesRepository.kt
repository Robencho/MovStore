package com.rubio.movstore.data.repository

import com.rubio.movstore.domain.entities.Movie

interface IMoviesRepository {
    suspend fun getMovies(
        category: String?,
        response: (data: List<Movie>?) -> Unit
    )
}