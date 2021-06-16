package com.bnawan.saferoute.entity

import android.os.Parcel
import android.os.Parcelable

data class Ruangan(
    val nama: String?,
    val gambar: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(nama)
        p0.writeValue(gambar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ruangan> {
        override fun createFromParcel(parcel: Parcel): Ruangan {
            return Ruangan(parcel)
        }

        override fun newArray(size: Int): Array<Ruangan?> {
            return arrayOfNulls(size)
        }
    }
}