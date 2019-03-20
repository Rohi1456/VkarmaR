package com.vkarmaedu.vkarma.dataModels

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Student(
    val UId: String, val rollNo: Int, val name: String, val batch: String, val age: String,
    val father: String, val mother: String, val email: String, val address: String, val phoneNo: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    constructor() : this("", -1, "", "", "", "", "", "", "", "") {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(UId)
        parcel.writeInt(rollNo)
        parcel.writeString(name)
        parcel.writeString(batch)
        parcel.writeString(age)
        parcel.writeString(father)
        parcel.writeString(mother)
        parcel.writeString(email)
        parcel.writeString(address)
        parcel.writeString(phoneNo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}