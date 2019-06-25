package com.manuelbacallado.gymprogress.models

import android.os.Parcel
import android.os.Parcelable

data class Routine(var routineId : Int, var name : String, var startDate : String, var finishDate : String,
                   var trainingDays : Int, var trainingTypes : String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(routineId)
        parcel.writeString(name)
        parcel.writeString(startDate)
        parcel.writeString(finishDate)
        parcel.writeInt(trainingDays)
        parcel.writeString(trainingTypes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Routine> {
        override fun createFromParcel(parcel: Parcel) = Routine(parcel)

        override fun newArray(size: Int) = arrayOfNulls<Routine>(size)
    }
}