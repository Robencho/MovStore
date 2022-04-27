package com.rubio.movstore.data.models

import com.rubio.movstore.domain.entities.Movie
import com.rubio.movstore.domain.entities.MoviesResponse

fun Movie.toMovieParcelable() = MovieParcelable(
    null,
    adult,
    backdropPath,
    id,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    title,
    video,
    voteAverage,
    voteCount
)

fun MoviesResponse.toMovieResponseParcelable() = MovieResponseParcelable(
    page,
    results.toListMovieParcelable(results),
    totalPages,
    totalResults
)

fun MovieParcelable.toMovieEntity() = Movie(
    adult,
    backdropPath,
    id,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    title,
    video,
    voteAverage,
    voteCount
)

fun MovieResponseParcelable.toMovieResponseEntity() = MoviesResponse(
    page,
    results.toListMovies(results),
    totalPages,
    totalResults
)

fun List<MovieParcelable>.toListMovies(movies: List<MovieParcelable>): List<Movie> {
    val listMovies = mutableListOf<Movie>()
    movies.forEach { listMovies.add(it.toMovieEntity()) }
    return listMovies
}

fun List<Movie>.toListMovieParcelable(movies: List<Movie>): List<MovieParcelable> {
    val listMoviesParcelable = mutableListOf<MovieParcelable>()
    movies.forEach { listMoviesParcelable.add(it.toMovieParcelable()) }
    return listMoviesParcelable
}