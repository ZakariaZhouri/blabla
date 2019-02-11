package com.example.blablacartest.domain

import com.example.blablacartest.data.model.Client
import com.example.blablacartest.data.model.ClientOutput
import com.example.blablacartest.data.model.Trips
import retrofit2.Call

interface Repository {
    fun getClientToken(client: Client): Call<ClientOutput>
    fun saveToken(token: String)
    fun search(departure: String, arrival: String): Call<Trips>
}