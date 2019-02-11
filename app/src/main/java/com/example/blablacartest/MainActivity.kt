package com.example.blablacartest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.blablacartest.platform.BlablaModule
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val module = BlablaModule(this)
        test.setOnClickListener {
            module.controller.getToken()
        }
        testSearch.setOnClickListener {
            module.controller.getTrips()
        }
    }
}
