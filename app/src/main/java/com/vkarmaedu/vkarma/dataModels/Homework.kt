package com.vkarmaedu.vkarma.dataModels

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Homework (val subName : String, val teachName : String, val batch: String,
                     val date : Long, val text : String, val attachment : String? ) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString()
    )

    constructor() : this("", "","", 0, "", null)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(subName)
        parcel.writeString(teachName)
        parcel.writeString(batch)
        parcel.writeLong(date)
        parcel.writeString(text)
        parcel.writeString(attachment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Homework> {
        override fun createFromParcel(parcel: Parcel): Homework {
            return Homework(parcel)
        }

        override fun newArray(size: Int): Array<Homework?> {
            return arrayOfNulls(size)
        }
    }
}