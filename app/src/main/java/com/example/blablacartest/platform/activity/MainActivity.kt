package com.example.blablacartest.platform.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.blablacartest.R
import com.example.blablacartest.platform.BlablaModule
import com.example.blablacartest.presentation.TripsViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), com.example.blablacartest.presentation.View {
    companion object {
        const val LIST_TRIP = "listTrip"
        const val BUNDLE = "bundle"
    }

    var module: BlablaModule? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        module = BlablaModule(this, this)
        searchButton.setOnClickListener {
            if (textVerificationAndDisplauyError()) {
                progress.visibility = View.VISIBLE
                loaderView.visibility = View.VISIBLE
                searchButton.visibility = View.GONE
                getTrips()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        module?.controller?.getToken()
    }

    private fun textVerificationAndDisplauyError(): Boolean {
        if (departureText.text.toString().isEmpty()) {
            departureText.error = this.getString(R.string.edit_text_error)
            return false
        } else if (arrivalText.text.toString().isEmpty()) {
            arrivalText.error = this.getString(R.string.edit_text_error)
            return false
        }
        return true
    }

    override fun showError(errorMessage: String) {
        createErrorPopUp()
        progress.visibility = View.GONE
        loaderView.visibility = View.GONE
    }

    override fun showTrips(viewModel: List<TripsViewModel>) {
        progress.visibility = View.GONE
        loaderView.visibility = View.GONE
        val intent = Intent(this, TripsActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelableArrayList(LIST_TRIP, viewModel as ArrayList<out Parcelable>?)
        intent.putExtra(BUNDLE, bundle)
        startActivity(intent)
    }

    private fun getTrips() {
        val departure = departureText.text.toString()
        val arrival = arrivalText.text.toString()
        module?.controller?.getTrips(departure, arrival)
    }

    private fun createErrorPopUp() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.error_text)
            .setPositiveButton(
                R.string.error_yes
            ) { dialog, id ->
                getTrips()
            }
            .setNegativeButton(
                R.string.error_no
            ) { dialog, id ->
                dialog.cancel();
            }
        builder.create()
    }
}
