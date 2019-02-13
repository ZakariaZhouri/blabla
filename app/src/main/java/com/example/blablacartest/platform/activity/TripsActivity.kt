package com.example.blablacartest.platform.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.blablacartest.R
import com.example.blablacartest.platform.recycler.TripsRecyclerAdapter
import com.example.blablacartest.presentation.TripsViewModel
import kotlinx.android.synthetic.main.activity_trips.*


class TripsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)
        val bundle = intent.extras?.getBundle(MainActivity.BUNDLE)
        val listTrip = bundle?.getParcelableArrayList<TripsViewModel>(MainActivity.LIST_TRIP)
        tripRecycler.adapter = TripsRecyclerAdapter(listTrip)
        tripRecycler.layoutManager = LinearLayoutManager(this)
    }
}