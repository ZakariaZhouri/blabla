package com.example.blablacartest.data.model

import com.google.gson.annotations.SerializedName

data class Trip(
    @SerializedName("departure_date") val departureDate: String
)
