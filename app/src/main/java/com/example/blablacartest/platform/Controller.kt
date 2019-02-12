package com.example.blablacartest.platform

interface Controller {

    fun getToken()
    fun getTrips(departure : String, arrival : String)
}