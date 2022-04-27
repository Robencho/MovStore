package com.rubio.movstore.data.models

import com.google.gson.annotations.SerializedName

class MovieResponseParcelable(
    val page: Int,
    val results: List<MovieParcelable>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)