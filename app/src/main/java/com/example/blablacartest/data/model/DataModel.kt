package com.example.blablacartest.data.model

import com.google.gson.annotations.SerializedName


class DataModel(@SerializedName("trips") val trips: List<Trip>)

data class Trip(
    @SerializedName("user") val user: User,
    @SerializedName("price_without_commission") val price: Price,
    @SerializedName("seats") val seats: String,
    @SerializedName("seats_left") val seatsLeft: String,
    @SerializedName("departure_date") val departureDate: String,
    @SerializedName("departure_place") val departurePlace: Adress,
    @SerializedName("arrival_place") val arrivalPlace: Adress

)

data class User(
    @SerializedName("display_name") val driverName: String,
    @SerializedName("picture") val driverPicture: String
)

data class Price(
    @SerializedName("value") val price: String
)


data class Adress(
    @SerializedName("city_name") val cityName: String,
    @SerializedName("address") val adress: String
)