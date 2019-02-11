package com.example.blablacartest.platform

import android.content.Context
import com.example.blablacartest.data.BlablacarRepository
import com.example.blablacartest.domain.BlablacarInteractor

class BlablaModule(context: Context) {


    var interactor: BlablacarInteractor
    val controller: BlablaCarController
    val repository: BlablacarRepository

    init {
        repository = BlablacarRepository(context)
        interactor = BlablacarInteractor(repository)
        controller = BlablaCarController(interactor)
    }
}