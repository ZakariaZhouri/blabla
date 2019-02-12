package com.example.blablacartest.presentation

interface View {

    fun showError(errorMessage: String)
    fun showTrips(viewModels: List<TripsViewModel>)
}