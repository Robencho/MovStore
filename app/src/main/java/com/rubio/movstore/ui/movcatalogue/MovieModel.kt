package com.rubio.movstore.ui.movcatalogue

data class MovieModel(
    val imageDetailMovie: String? = "",
    val titleMovieDetail: String? = "",
    val originalLanguage: String? = "",
    val releaseDate: String? = "",
    val voteAverage: Float? = 0.0F,
    val voteCount: Int? = 0,
    val overView:String? = ""
)