package com.bnawan.saferoute.db

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.bnawan.saferoute.db.DatabaseContract.TfObjekColumns.Companion.TABLE_NAME
import com.bnawan.saferoute.db.DatabaseContract.TfObjekColumns.Companion._ID

class TfObjekHelper(context: Context) {
    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME

        private var INSTANCE: TfObjekHelper? = null
        fun getInstance(context: Context): TfObjekHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: TfObjekHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()
        if (database.isOpen)
            database.close()
    }

    fun queryById(id: Int): Cursor {
        return database.query(DATABASE_TABLE, null, "$_ID = ?",
            arrayOf(id.toString()), null, null, null, null)
    }
}