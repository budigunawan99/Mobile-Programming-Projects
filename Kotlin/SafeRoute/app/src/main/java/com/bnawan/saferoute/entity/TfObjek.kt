package com.bnawan.saferoute.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TfObjek(
    val id: Int? = 999,
    val nama: String? = null,
    val tinggi: Int? = 0
) : Parcelable