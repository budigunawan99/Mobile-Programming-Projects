package id.ac.unsyiah.android.praktikum4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "supermarket.db";

    public static final String TABLE_BARANG = "tb_barang";

    public static final String COL_NAMABARANG = "nama_barang";
    public static final String COL_KODEBARANG = "kode_barang";
    public static final String COL_JUMLAH = "jumlah_barang";
    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_BARANG;


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE= "CREATE TABLE "+ TABLE_BARANG +" ("
                + COL_KODEBARANG +" VARCHAR, "+ COL_NAMABARANG +" VARCHAR, "
                + COL_JUMLAH+" INT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }
}
