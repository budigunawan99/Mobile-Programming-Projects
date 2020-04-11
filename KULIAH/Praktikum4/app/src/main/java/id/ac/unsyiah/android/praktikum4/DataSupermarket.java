package id.ac.unsyiah.android.praktikum4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataSupermarket {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Context context;

    public DataSupermarket(Context context) {
        this.context=context;
        databaseHelper=new DatabaseHelper(context);
    }

    public void buka() {
        db=databaseHelper.getWritableDatabase();
    }

    public void tutup(){
        db.close();
    }

    public void addBarang(Supermarket supermarket) {
        ContentValues cv = new ContentValues();
        cv.put(databaseHelper.COL_KODEBARANG, supermarket.getKodeBarang());
        cv.put(databaseHelper.COL_NAMABARANG, supermarket.getNamaBarang());
        cv.put(databaseHelper.COL_JUMLAH, supermarket.getJumlah());
        db.insert(databaseHelper.TABLE_BARANG, null, cv);

    }

    public Supermarket getBarang (String kode) {
        String query = "SELECT * FROM "+DatabaseHelper.TABLE_BARANG+
                " WHERE "+DatabaseHelper.COL_KODEBARANG+" = ?";

        Cursor cursor = db.rawQuery(query, new String[]{kode});
        Supermarket barang = new Supermarket();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            barang.setKodeBarang(cursor.getString(0));
            barang.setNamaBarang(cursor.getString(1));
            barang.setJumlah(cursor.getInt(2));
        } else {
            barang = null;
        }
        cursor.close();
        return barang;
    }

    public Supermarket[] getAllBarang() {
        Cursor cursor = db.query(DatabaseHelper.TABLE_BARANG, new String[]{DatabaseHelper.COL_KODEBARANG, DatabaseHelper.COL_NAMABARANG,
        DatabaseHelper.COL_JUMLAH},
                null, null, null, null, null);
        int count = cursor.getCount();
        Supermarket[] listBarang = new Supermarket[count];
        if (count > 0) {
            int x =0;
            cursor.moveToFirst();
            do {
                listBarang[x] = new Supermarket();
                listBarang[x].setKodeBarang(cursor.getString(0));
                listBarang[x].setNamaBarang(cursor.getString(1));
                listBarang[x].setJumlah(cursor.getInt(2));
                x++;
            } while (cursor.moveToNext());
        } else {
            listBarang = null;
        }
        cursor.close();
        return listBarang;
    }

    public boolean updateBarang (Supermarket supermarket) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COL_JUMLAH, supermarket.getJumlah());
        cv.put(DatabaseHelper.COL_NAMABARANG, supermarket.getNamaBarang());
        return db.update(DatabaseHelper.TABLE_BARANG, cv, DatabaseHelper.COL_KODEBARANG+ " = ?",
                new String[]{supermarket.getKodeBarang()}) > 0;
    }

    public boolean deleteBarang (String kode) {
        Supermarket barang = getBarang(kode);
        if (barang != null) {
            return db.delete(DatabaseHelper.TABLE_BARANG, DatabaseHelper.COL_KODEBARANG + " = ?",
                    new String[]{barang.getKodeBarang()}) > 0;
        }
        return false;
    }
}
