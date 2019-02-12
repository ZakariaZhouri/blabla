package com.example.blablacartest.domain

import com.example.blablacartest.data.model.Trip

interface OutPutPort {

    fun showSearchResult(tripList: List<Trip>?)
    fun showError()
}