package com.example.blablacartest.domain

import android.util.Log
import com.example.blablacartest.data.model.Client
import com.example.blablacartest.data.model.ClientOutput
import com.example.blablacartest.data.model.DataModel
import com.example.blablacartest.data.model.Trip
import org.json.JSONObject
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
            }

        })
    }

    fun search(departure: String, arrival: String) {
        repository.search(departure, arrival).enqueue(object : Callback<DataModel> {
            override fun onFailure(call: Call<DataModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                val trips = response.body()?.trips
                trips?.let {
                    orderTripsWithPrice(it)
                }

            }

        })
    }

    private fun orderTripsWithPrice(trips: List<Trip>): List<Trip> {
       val result =  trips.sortedBy { trip ->
            trip.price.price.toFloat()
        }

        return result
    }
}
