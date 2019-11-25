package net.myanmarlinks.retrofittest.movies.moviemodel

import com.google.gson.annotations.SerializedName

data class Movie(
    val poster_path: String,
    val id: Int,
    val title: String,
    @SerializedName("vote_average")
    val voteAvg: Double,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String
)