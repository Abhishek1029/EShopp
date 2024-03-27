package com.futurecoder.eshopp.data

import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("rate") var rate: Double? = null,
    @SerializedName("count") var count: Int? = null
)
