package com.app.grckikino.models

import android.os.Parcel
import android.os.Parcelable

class WinningNumbers() : Parcelable {

    var bonus: List<Int> = emptyList()
    var list: List<Int> = emptyList()

    // Konstruktor za Parcel
    constructor(parcel: Parcel) : this() {
        bonus =  parcel.createIntArray()?.toList() ?: emptyList()
        list = parcel.createIntArray()?.toList() ?: emptyList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeIntArray(list.toIntArray())
        parcel.writeIntArray(list.toIntArray())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WinningNumbers> {
        override fun createFromParcel(parcel: Parcel): WinningNumbers {
            return WinningNumbers(parcel)
        }

        override fun newArray(size: Int): Array<WinningNumbers?> {
            return arrayOfNulls(size)
        }
    }
}