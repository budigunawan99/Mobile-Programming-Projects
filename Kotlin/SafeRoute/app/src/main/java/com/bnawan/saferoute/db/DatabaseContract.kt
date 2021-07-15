package com.bnawan.saferoute.db

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class TfObjekColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "tfobjek"
            const val _ID = "_id"
            const val NAMA = "nama"
            const val TINGGI = "tinggi"
        }
    }
}