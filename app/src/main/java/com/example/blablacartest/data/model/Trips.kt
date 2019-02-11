package com.example.blablacartest.data.model

import com.google.gson.annotations.SerializedName


data class Trips(
    @SerializedName("sorting_algorithm") val links: String?
)

data class Links(
    @SerializedName("_self") val self: String?
)

