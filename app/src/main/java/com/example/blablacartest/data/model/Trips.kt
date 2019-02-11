package com.example.blablacartest.data.model

import com.google.gson.annotations.SerializedName


data class Trips(
    @SerializedName("trips") val trip: List<Trip>
)

data class Links(
    @SerializedName("_self") val self: String?
)

