package com.example.blablacartest.presentation

import android.os.Parcel
import android.os.Parcelable

data class TripsViewModel(
    val departure: String,
    val arrival: String,
    val driverName: String,
    val driverPicture: String?,
    val price: String,
    val departureDate: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(departure)
        parcel.writeString(arrival)
        parcel.writeString(driverName)
        parcel.writeString(driverPicture)
        parcel.writeString(price)
        parcel.writeString(departureDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TripsViewModel> {
        override fun createFromParcel(parcel: Parcel): TripsViewModel {
            return TripsViewModel(parcel)
        }

        override fun newArray(size: Int): Array<TripsViewModel?> {
            return arrayOfNulls(size)
        }
    }


}