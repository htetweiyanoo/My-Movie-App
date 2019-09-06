package net.myanmarlinks.retrofittest.model

import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("_id")
    val id: String,
    val title: String,
    val name: String,
    val profile_pic: String
)