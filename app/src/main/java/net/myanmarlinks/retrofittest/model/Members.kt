package net.myanmarlinks.retrofittest.model

import com.google.gson.annotations.SerializedName

class Members(
    @SerializedName("data")
    val members: List<Member>
)