package com.example.blablacartest.data.api

import com.example.blablacartest.data.model.Client
import com.example.blablacartest.data.model.ClientOutput
import com.example.blablacartest.data.model.DataModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface BlablaApi {
    @POST("token")
    fun getClientToken(@Body client: Client): Call<ClientOutput>

    @GET("api/v2/trips")
    fun getTrips(
        @HeaderMap headers: Map<String, String>,
        @Query("_format") format: String,
        @Query("locale") locale: String,
        @Query("cur") cur: String,
        @Query("fn") departure: String,
        @Query("tn") arrival: String
    ): Call<DataModel>
}