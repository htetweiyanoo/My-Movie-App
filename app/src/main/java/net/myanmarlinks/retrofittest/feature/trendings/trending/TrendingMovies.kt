package net.myanmarlinks.retrofittest.trendings.trending

import com.google.gson.annotations.SerializedName

data class TrendingMovies(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingMovies: List<TrendingMovie>
)