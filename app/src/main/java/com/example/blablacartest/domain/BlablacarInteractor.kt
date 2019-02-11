package com.example.blablacartest.domain

import android.util.Log
import com.example.blablacartest.data.model.Client
import com.example.blablacartest.data.model.ClientOutput
import com.example.blablacartest.data.model.Trips
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlablacarInteractor(val repository: Repository) {

    fun getToken(client: Client) {

        val clientOutput = repository.getClientToken(client)
        clientOutput.enqueue(object : Callback<ClientOutput> {
            override fun onFailure(call: Call<ClientOutput>, t: Throwable) {

            }

            override fun onResponse(call: Call<ClientOutput>, response: Response<ClientOutput>) {
                val token = response.body()?.token
                token?.let {
                    repository.saveToken(it)
                }
                search("Paris", "Rennes")
            }

        })
    }

    fun search(departure: String, arrival: String) {
        repository.search(departure, arrival).enqueue(object : Callback<Trips> {
            override fun onFailure(call: Call<Trips>, t: Throwable) {

            }

            override fun onResponse(call: Call<Trips>, response: Response<Trips>) {
                Log.e("TEST", "TEST")
            }

        })
    }
}