package net.myanmarlinks.retrofittest.model.trending

import com.google.gson.annotations.SerializedName

data class Trendings(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
)