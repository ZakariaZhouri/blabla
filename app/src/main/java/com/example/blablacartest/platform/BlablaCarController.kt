package com.example.blablacartest.platform

import com.example.blablacartest.data.model.Client
import com.example.blablacartest.domain.BlablacarInteractor

class BlablaCarController(val interactor: BlablacarInteractor) : Controller {


    override fun getTrips(departure: String, arrival: String) {
        interactor.search("Paris", "Rennes")
    }

    override fun getToken() {
        interactor.getToken(
            Client(
                "client_credentials",
                "android-technical-tests",
                "Y1oAL3QdPfVhGOWj3UeDjo3q02Qwhvrj"
            )
        )
    }
}