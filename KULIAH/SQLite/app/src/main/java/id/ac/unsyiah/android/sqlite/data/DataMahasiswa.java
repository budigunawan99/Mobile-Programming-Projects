package id.ac.unsyiah.android.sqlite.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataMahasiswa {

    private SQLiteDatabase database;
    private DatabaseHelper dbhelper;

    public DataMahasiswa(Context context){
        dbhelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException{
        database = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public void addMahasiswa(Mahasiswa mahasiswa){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_NAMA, mahasiswa.getNama());
        values.put(DatabaseHelper.KEY_NIM, mahasiswa.getNim());

        //inserting row
        database.insert(DatabaseHelper.TABLE_MAHASISWA, null, values);
    }

    public List<Mahasiswa> getAllMahasiswa(){
        List<Mahasiswa> listMahasiswa = new ArrayList<Mahasiswa>();

        //select all data mahasiswa
        String allMahasiswa = "SELECT * FROM " + DatabaseHelper.TABLE_MAHASISWA;
        Cursor cursor = database.rawQuery(allMahasiswa, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setId(Integer.parseInt(cursor.getString(0)));
                mahasiswa.setNim(cursor.getString(1));
                mahasiswa.setNama(cursor.getString(2));

                //adding mahasiswa to the list
                listMahasiswa.add(mahasiswa);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listMahasiswa;
    }

}