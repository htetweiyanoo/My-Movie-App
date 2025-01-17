package net.myanmarlinks.retrofittest.movies.moviemodel

import com.google.gson.annotations.SerializedName

data class Movies(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    @SerializedName("results")
    val movies: List<Movie>
)