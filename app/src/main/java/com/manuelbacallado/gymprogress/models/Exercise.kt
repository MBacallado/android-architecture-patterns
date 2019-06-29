package com.manuelbacallado.gymprogress.models

import android.os.Parcel
import android.os.Parcelable

data class Exercise(var exerciseId: Int, var exerciseName: String, var initialWeight: Int, var finalWeight: Int, var trainingDayId: Int) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(exerciseId)
        parcel.writeString(exerciseName)
        parcel.writeInt(initialWeight)
        parcel.writeInt(finalWeight)
        parcel.writeInt(trainingDayId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Exercise> {
        override fun createFromParcel(parcel: Parcel): Exercise {
            return Exercise(parcel)
        }

        override fun newArray(size: Int): Array<Exercise?> {
            return arrayOfNulls(size)
        }
    }
}