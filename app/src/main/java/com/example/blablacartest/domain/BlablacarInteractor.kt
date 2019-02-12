package com.example.blablacartest.domain

import com.example.blablacartest.data.model.Client
import com.example.blablacartest.data.model.ClientOutput
import com.example.blablacartest.data.model.DataModel
import com.example.blablacartest.data.model.Trip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlablacarInteractor(val repository: Repository, val outPutPort: OutPutPort) {

    fun getToken(client: Client) {

        val clientOutput = repository.getClientToken(client)
        clientOutput.enqueue(object : Callback<ClientOutput> {
            override fun onFailure(call: Call<ClientOutput>, t: Throwable) {
                outPutPort.showError()
            }

            override fun onResponse(call: Call<ClientOutput>, response: Response<ClientOutput>) {
                val token = response.body()?.token
                token?.let {
                    repository.saveToken(it)
                } ?: outPutPort.showError()
            }

        })
    }

    fun search(departure: String, arrival: String) {
        repository.search(departure, arrival).enqueue(object : Callback<DataModel> {
            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                outPutPort.showError()
            }

            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                val trips = response.body()?.trips
                trips?.let {
                    val result = orderTripsWithPrice(it)
                    outPutPort.showSearchResult(result)
                } ?: outPutPort.showError()


            }

        })
    }

    private fun orderTripsWithPrice(trips: List<Trip>): List<Trip> {
        return trips.sortedBy { trip ->
            trip.price.price.toFloat()
        }
    }
}
