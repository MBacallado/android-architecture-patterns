package com.manuelbacallado.gymprogress.models

import android.os.Parcel
import android.os.Parcelable

data class TrainingDay(var trainingDayId : Int, var day: String, var timeAmount : Int ,var group : String,
                       var routineId: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(trainingDayId)
        parcel.writeString(day)
        parcel.writeInt(timeAmount)
        parcel.writeString(group)
        parcel.writeInt(routineId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrainingDay> {
        override fun createFromParcel(parcel: Parcel): TrainingDay {
            return TrainingDay(parcel)
        }

        override fun newArray(size: Int): Array<TrainingDay?> {
            return arrayOfNulls(size)
        }
    }
}