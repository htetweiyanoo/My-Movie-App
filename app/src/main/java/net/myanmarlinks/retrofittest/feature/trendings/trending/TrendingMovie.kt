package net.myanmarlinks.retrofittest.trendings.trending

import com.google.gson.annotations.SerializedName

data class TrendingMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
)