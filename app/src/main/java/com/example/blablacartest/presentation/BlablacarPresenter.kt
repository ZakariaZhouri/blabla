package com.example.blablacartest.presentation

import android.content.res.Resources
import com.example.blablacartest.R
import com.example.blablacartest.data.model.Trip
import com.example.blablacartest.domain.OutPutPort

class BlablacarPresenter(val resources: Resources, val view: View) : OutPutPort {
    override fun showSearchResult(tripList: List<Trip>?) {
        val listViewModel = ArrayList<TripsViewModel>()
        tripList?.let {
            it.forEach {
                val viewModel = TripsViewModel(
                    it.departurePlace.adress,
                    it.arrivalPlace.adress, it.user.driverName, it.user.driverPicture,
                    it.price.price, it.departureDate
                )
                listViewModel.add(viewModel)
            }
        }

        view.showTrips(listViewModel)
    }

    override fun showError() {
        val errorMessage = resources.getString(R.string.error_text)
        view.showError(errorMessage)
    }
}