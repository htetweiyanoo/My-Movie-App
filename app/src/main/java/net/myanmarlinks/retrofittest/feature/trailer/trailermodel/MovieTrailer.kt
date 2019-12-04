package net.myanmarlinks.retrofittest.feature.trailer

import com.google.gson.annotations.SerializedName

data class MovieTrailer (
    @SerializedName("id")
    val id: Int,
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size")
    val size: Int
)