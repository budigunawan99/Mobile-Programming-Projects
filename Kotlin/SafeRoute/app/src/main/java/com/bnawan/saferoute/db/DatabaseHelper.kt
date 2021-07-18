package com.bnawan.saferoute.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.bnawan.saferoute.db.DatabaseContract.TfObjekColumns
import com.bnawan.saferoute.db.DatabaseContract.TfObjekColumns.Companion.TABLE_NAME

internal class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dbsaferoute"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_OBJECT = "CREATE TABLE $TABLE_NAME" +
                " (${TfObjekColumns._ID} INTEGER PRIMARY KEY," +
                " ${TfObjekColumns.NAMA} TEXT NOT NULL," +
                " ${TfObjekColumns.TINGGI} INTEGER NOT NULL)"

        private const val SQL_INSERT_INITIAL_VALUE = "INSERT INTO $TABLE_NAME" +
                "(${TfObjekColumns._ID}, ${TfObjekColumns.NAMA}, ${TfObjekColumns.TINGGI}) VALUES" +
                "(0, 'person', 165)," +
                "(10, 'fire hydrant', 46)," +
                "(12, 'stop sign', 46)," +
                "(13, 'parking meter', 122)," +
                "(14, 'bench', 63)," +
                "(26, 'backpack', 50)," +
                "(27, 'umbrella', 88)," +
                "(32, 'suitcase', 62)," +
                "(36, 'sports ball', 22)," +
                "(51, 'banana', 4)," +
                "(61, 'chair', 86)," +
                "(62, 'couch', 94)," +
                "(63, 'potted plant', 63)," +
                "(64, 'bed', 68)," +
                "(66, 'dining table', 78)," +
                "(71, 'tv', 75)," +
                "(77, 'microwave', 58)," +
                "(78, 'oven', 74)," +
                "(79, 'toaster', 40)," +
                "(80, 'sink', 27)," +
                "(81, 'refrigerator', 172)," +
                "(84, 'clock', 46)," +
                "(86, 'vase', 33)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_OBJECT)
        db.execSQL(SQL_INSERT_INITIAL_VALUE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}