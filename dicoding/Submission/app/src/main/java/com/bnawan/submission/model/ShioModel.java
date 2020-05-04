package com.bnawan.submission.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class ShioModel {
    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    public ShioModel(Context context) {
        this.context = context;
        databaseHelper = DatabaseHelper.getInstance(context);
//        databaseHelper = new DatabaseHelper(context);
    }

    public void buka() {
        db = databaseHelper.getWritableDatabase();
    }

    public void tutup() {
        db.close();
    }

    public ArrayList<Shio> getListData(){
        ArrayList<Shio> list = new ArrayList<>();
        this.buka();

        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, new String[]{DatabaseHelper.COL_ID, DatabaseHelper.COL_NAMA,
                        DatabaseHelper.COL_DETAIL, DatabaseHelper.COL_DESKRIPSI, DatabaseHelper.COL_GAMBAR},null, null, null, null, null);

        cursor.moveToFirst();
        do {
            Shio shio = new Shio();
            shio.setId(cursor.getInt(0));
            shio.setName(cursor.getString(1));
            shio.setDetail(cursor.getString(2));
            shio.setDeskripsi(cursor.getString(3));
            shio.setImage(cursor.getInt(4));
            list.add(shio);
        } while (cursor.moveToNext());

        this.tutup();
        return list;
    }
}
