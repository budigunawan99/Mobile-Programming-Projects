package com.bnawan.saferoute.utils

import android.database.Cursor
import com.bnawan.saferoute.db.DatabaseContract
import com.bnawan.saferoute.entity.TfObjek

object MappingHelper {

    fun mapCursorToTfObjek(tfObjekCursor: Cursor?): TfObjek {
        var tfobjek = TfObjek()
        tfObjekCursor?.apply {
            if(moveToFirst()){
                val _id = getInt(getColumnIndexOrThrow(DatabaseContract.TfObjekColumns._ID))
                val nama = getString(getColumnIndexOrThrow(DatabaseContract.TfObjekColumns.NAMA))
                val tinggi = getInt(getColumnIndexOrThrow(DatabaseContract.TfObjekColumns.TINGGI))
                tfobjek = TfObjek(_id, nama, tinggi)
            }
        }
        return tfobjek
    }
}