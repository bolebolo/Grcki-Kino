package com.app.grckikino.models

import android.os.Parcel
import android.os.Parcelable

class RoundsHistoryModel() : Parcelable {

    var content: ArrayList<RoundsModel> = arrayListOf()
    var totalPages: Int = 0
    var totalElements: Int = 0
    var numberOfElements: Int = 0

    // Konstruktor za Parcel
    constructor(parcel: Parcel) : this() {
        content = parcel.createTypedArrayList(RoundsModel.CREATOR) ?: arrayListOf()
        totalPages = parcel.readInt()
        totalElements = parcel.readInt()
        numberOfElements = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(content)
        parcel.writeInt(totalPages)
        parcel.writeInt(totalElements)
        parcel.writeInt(numberOfElements)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RoundsHistoryModel> {
        override fun createFromParcel(parcel: Parcel): RoundsHistoryModel {
            return RoundsHistoryModel(parcel)
        }

        override fun newArray(size: Int): Array<RoundsHistoryModel?> {
            return arrayOfNulls(size)
        }
    }
}