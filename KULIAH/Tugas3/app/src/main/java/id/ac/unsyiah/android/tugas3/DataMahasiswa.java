package id.ac.unsyiah.android.tugas3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataMahasiswa {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Context context;

    public DataMahasiswa(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public void buka() {
        db = databaseHelper.getWritableDatabase();
    }

    public void tutup() {
        db.close();
    }

    public void addMahasiwa(Mahasiswa mahasiswa) {
        ContentValues cv = new ContentValues();
        cv.put(databaseHelper.COL_NO, mahasiswa.getNo());
        cv.put(databaseHelper.COL_NPM, mahasiswa.getNpm());
        cv.put(databaseHelper.COL_NAMA, mahasiswa.getNama());
        cv.put(databaseHelper.COL_JURUSAN, mahasiswa.getJurusan());
        cv.put(databaseHelper.COL_IMAGE, mahasiswa.getImage());
        db.insert(databaseHelper.TABLE_NAME, null, cv);

    }

    public Mahasiswa getIdentitas (String kode) {
        String query = "SELECT * FROM "+DatabaseHelper.TABLE_NAME+
                " WHERE "+DatabaseHelper.COL_NO+" = ?";

        Cursor cursor = db.rawQuery(query, new String[]{kode});
        Mahasiswa identitas = new Mahasiswa();
        if (cursor.moveToFirst()) {
            Log.d("getIdentitas","onCreate");
            cursor.moveToFirst();
            identitas.setNo(cursor.getInt(0));
            identitas.setNpm(cursor.getLong(1));
            identitas.setNama(cursor.getString(2));
            identitas.setJurusan(cursor.getString(3));
            identitas.setImage(cursor.getBlob(4));
        } else {
            identitas = null;
        }
        cursor.close();
        return identitas;
    }

    public Mahasiswa[] getAllIdentitas() {
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, new String[]{DatabaseHelper.COL_NO, DatabaseHelper.COL_NPM,
                        DatabaseHelper.COL_NAMA, DatabaseHelper.COL_JURUSAN},
                null, null, null, null, null);
        int count = cursor.getCount();
        Mahasiswa[] listIdentitas = new Mahasiswa[count];
        if (count > 0) {
            int x =0;
            cursor.moveToFirst();
            do {
                listIdentitas[x] = new Mahasiswa();
                listIdentitas[x].setNo(cursor.getInt(0));
                listIdentitas[x].setNpm(cursor.getLong(1));
                listIdentitas[x].setNama(cursor.getString(2));
                listIdentitas[x].setJurusan(cursor.getString(3));
                x++;
            } while (cursor.moveToNext());
        } else {
            listIdentitas = null;
        }
        cursor.close();
        return listIdentitas;
    }

    public boolean updateIdentitas (Mahasiswa mahasiswa) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COL_NAMA, mahasiswa.getNama());
        cv.put(DatabaseHelper.COL_JURUSAN, mahasiswa.getJurusan());
        return db.update(DatabaseHelper.TABLE_NAME, cv, DatabaseHelper.COL_NO+ " = ?",
                new String[]{Integer.toString(mahasiswa.getNo())}) > 0;
    }

    public boolean deleteIdentitas (String kode) {
        Mahasiswa identitas = getIdentitas(kode);
        if (identitas != null) {
            return db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COL_NO + " = ?",
                    new String[]{Integer.toString(identitas.getNo())}) > 0;
        }
        return false;
    }

}
