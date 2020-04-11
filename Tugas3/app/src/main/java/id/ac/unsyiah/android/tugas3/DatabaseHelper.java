package id.ac.unsyiah.android.tugas3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME = "mahasiswa";

    public static final String TABLE_NAME = "identitas";

    public static final String COL_NO = "no_mahasiswa";
    public static final String COL_NPM = "npm";
    public static final String COL_NAMA = "nama";
    public static final String COL_JURUSAN = "jurusan";
    public static final String COL_IMAGE = "image";
    private static final String SQL_DROP_TABLE ="DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_NO + " INT, " + COL_NPM + " LONG, "
                + COL_NAMA + " VARCHAR, " + COL_JURUSAN + " VARCHAR, " + COL_IMAGE + " BLOB)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }
}
