package com.example.blablacartest.platform.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.blablacartest.R
import com.example.blablacartest.presentation.TripsViewModel
import kotlinx.android.synthetic.main.trip_recycler.view.*

class TripsRecyclerAdapter(val listTripViewModel: List<TripsViewModel>?) :
    RecyclerView.Adapter<TripsRecyclerAdapter.TripsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripsViewHolder {
        val view = LayoutInflater.from(parent?.getContext()).inflate(R.layout.trip_recycler, parent, false)
        return TripsViewHolder(view)
    }

    override fun getItemCount(): Int {
        listTripViewModel?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: TripsViewHolder, position: Int) {
        listTripViewModel?.let { list ->
            holder.bindTripInformation(list[position])
        }
    }


    inner class TripsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTripInformation(tripsViewModel: TripsViewModel) {
            itemView.driverName.text = tripsViewModel.driverName
            itemView.departure.text = tripsViewModel.departure
            itemView.arrival.text = tripsViewModel.arrival
            itemView.departureDate.text = tripsViewModel.departureDate
            itemView.price.text = tripsViewModel.price + itemView.context.getString(R.string.euro)
            tripsViewModel.driverPicture?.let {
                Glide.with(itemView.context).load(it).into(itemView.driverPicture)
            }
        }
    }
}