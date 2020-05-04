package com.bnawan.submission.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bnawan.submission.R;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME = "db_bnawan";

    public static final String TABLE_NAME = "shio";

    public static final String COL_ID = "id_shio";
    public static final String COL_NAMA = "nama";
    public static final String COL_DETAIL = "detail";
    public static final String COL_DESKRIPSI = "deskripsi";
    public static final String COL_GAMBAR = "gambar";
    private static final String SQL_DROP_TABLE ="DROP TABLE IF EXISTS " + TABLE_NAME;
    private static DatabaseHelper databaseHelper;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return databaseHelper;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INT," + COL_NAMA + " VARCHAR, " + COL_DETAIL + " VARCHAR, " + COL_DESKRIPSI + " VARCHAR, " + COL_GAMBAR + " INT)";
        db.execSQL(CREATE_TABLE);
        insertShio(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }

    private void insertShio(SQLiteDatabase db){
        for (int i = 0; i < nama.length; i++) {
            int id = i;
            String nama = this.nama[i];
            String detail = this.detail[i];
            String deskripsi = this.deskripsi[i];
            int gambar = this.gambar[i];

            ContentValues v = new ContentValues();
            v.put(COL_ID, id);
            v.put(COL_NAMA, nama);
            v.put(COL_DETAIL, detail);
            v.put(COL_DESKRIPSI, deskripsi);
            v.put(COL_GAMBAR, gambar);
            db.insert(TABLE_NAME, null, v);
        }
    }

    private String[] nama ={
        "Shio Tikus", "Shio Kerbau","Shio Macan", "Shio Kelinci", "Shio Naga", "Shio Ular", "Shio Kuda", "Shio Kambing", "Shio Monyet", "Shio Ayam", "Shio Anjing", "Shio Babi"
    };
    private String[] detail ={
            "Lahir di Tahun 1924, 1936, 1948, 1960, 1972, 1984, 1996, 2008, 2020.",
            "Lahir di Tahun 1925, 1937, 1949, 1961, 1973, 1985, 1997, 2009.",
            "Lahir di Tahun 1926, 1938, 1950, 1962, 1974, 1986, 1998, 2010.",
            "Lahir di Tahun 1927, 1939, 1951, 1963, 1975, 1987, 1999, 2011.",
            "Lahir di Tahun 1916, 1928, 1940, 1952, 1964, 1976, 1988, 2000.",
            "Lahir di Tahun 1917, 1929, 1941, 1953, 1965, 1977, 1989, 2001.",
            "Lahir di Tahun 1918, 1930, 1942, 1954,1966, 1978, 1990, 2002, 2014.",
            "Lahir di Tahun 1919, 1931, 1943, 1955, 1967, 1979, 1991, 2003, 2015.",
            "Lahir di Tahun 1920, 1932, 1944, 1956, 1968, 1980, 1992, 2004, 2016.",
            "Lahir di Tahun 1921, 1933, 1945, 1957, 1969, 1981, 1993, 2005, 2017.",
            "Lahir di Tahun 1922, 1934, 1946, 1958, 1970, 1982, 1994, 2006, 2018.",
            "Lahir di Tahun 1923, 1935, 1947, 1959, 1971, 1983, 1995, 2007, 2019."
    };
    private String[] deskripsi={
            "Kamu imajinatif, menyenangkan dan benar benar murah hati terhadap orang yang kamu cintai. Namun, kamu cenderung mudah marah dan terlalu kritis. Kamu juga dikenal sebagai orang yang opportunis. Lahir dalam shio ini, kamu akan senang bekerja dalam sales atau penulis, kritikus atau penerbit.",
            "Dilahirkan sebagai pemimpin, kamu menjadi inspirasi untuk orang di sekitar kamu. Kamu memilih metode konservatif dan berbakat menggunakan tangan kamu. Sangat mengharapkan segala sesuatu berjalan sesuai dengan pilihan kamu. Shio kerbau akan sangat berhasil sebagai dokter, jendral atau penata rambut.",
            "Kamu sensitif, emosional dan mampu menciptakan cinta yang luar biasa. Namun, kamu memiliki kecenderungan mengambil semua dan menjadi kerasa kepala dengan apa yang kamu anggap benar; lebih dikenal dengan istilah pemberontak. Shio macan ini sangat cocok sebagai boss, penjelajah, pembalap atau matador.",
            "Kamu adalah orang yang baik, sangat patuh dan menyenangkan. Kamu memiliki kecenderungan menjadi sentimentil. Menjadi hati-hati dan konseratif, kamu sangat berhasil dalam bisnis dan juga menjadi pengacara, diplomat atau aktor yang baik.",
            "Penuh fitalitas dan antusias, Naga adalah individu yang popular meskipun dengan reputasi yang buruk dan ‘mulut besar’. Kamu sangat pandai, berbakat dan perfeksionis namun kualitas ini menjadikan kamu kompeten di lingkungan kamu. Kamu akan sangat cocok sebagai artis, pendeta atau politikus.",
            "Kaya di dalam kebijakan dan ramah, kamu sangat romantis dan berpikir dalam dan intuisi kamu memandu dengan kuat. Hindari sikap rakus terhadap uang. Jaga selera humor mengenai hidup kamu. Shio ular biasanya cocok sebagai guru, filosofi, penulis, psikiater dan peramal.",
            "Kapasitas kamu dalam bekerja keras sangat mengagumkan. Kamu adalah orang yang mandiri. Selain pandai dan ramah, kamu cenderung menjadi egois dan tajam dan harus menjaga diri agar tidak terlalu mementingkan diri sendiri. Shio kamu menandakan sukses sebagai petualang, peneliti, puitis atau politikus.",
            "Selain sering merasa salah tingkah dihadapan orang lain, Shio Kambing dapat menjadi teman yang menyenangkan. Kamu sangat elegan dan artistik namun menjadi yang pertama dalam memberikan komplain apapun. Singkirkan rasa pesimisme dan kekhawatiran dan coba untuk menjadi tidak tergantung terhadap kenyamanan materi. Kamu akan cocok sebagai aktor, pengebun atau penata rias.",
            "Kamu sangat pintar, karena sifat alami kamu yang luar biasa dan personalitas yang menarik, kamu sangat disukai. Monyet, bagaimanapun juga, harus menjaga agar tidak menjadi orang yang oportunis dan tidak dipercaya oleh orang lain. Shio kamu menjanjikan kesuksesan disegala bidang yang kamu coba.",
            "Ayam adalah pekerja keras; tegas dalam mengambil keputusan juga jarang mengutarakan pemikiranya. Oleh karena ini, kamu cenderung menjadi ekslusif terhadap orang lain. Kamu adalah pemimpi, pemilih busana yang baik dan ekstravaganza. Lahir dalam shio ini kamu dapat bahagia menjadi pemilik restoran, penerbit, tentara atau turis.",
            "Anjing tidak pernah mengecewakan kamu. Lahir dalam shio ini kamu sangat jujur, dan setia orang yang kamu cintai. Kamu selalu merasa khawatir, perkataan yang tajam dan cenderung mencari kesalahan. Namun kamu akan menjadi businessman, aktivis, guru atau polisi yang baik.",
            "Kamu adalah teman yang baik, orang yang pintar dengan kebutuhan yang kuat untuk mencapai tujuan apapun. Kamu sangat baik, toleran dan jujur namun juga mengharapkan hal yang sama dari orang lain, tentunya hal ini sangat naif. Pencarian kamu terhadap material dapat menjadi kegagalan. Babi akan sangat berhasil dalam bidang seni seperti penghibur atau juga pengacara."
    };
    private int[] gambar = {
            R.drawable.tikus,
            R.drawable.kerbau,
            R.drawable.harimau,
            R.drawable.kelinci,
            R.drawable.naga,
            R.drawable.ular,
            R.drawable.kuda,
            R.drawable.kambing,
            R.drawable.monyet,
            R.drawable.ayam,
            R.drawable.anjing,
            R.drawable.babi
    };

}
