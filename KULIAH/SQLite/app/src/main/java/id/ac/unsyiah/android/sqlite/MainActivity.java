package id.ac.unsyiah.android.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import id.ac.unsyiah.android.sqlite.data.DataMahasiswa;
import id.ac.unsyiah.android.sqlite.data.Mahasiswa;

public class MainActivity extends AppCompatActivity {

    private DataMahasiswa db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataMahasiswa(this);
        db.open();

        //insert data mahasiswa
        Log.d("Inserting: ", "Masukkan data mahasiswa......");
        db.addMahasiswa(new Mahasiswa("1608107010018", "Andika"));
        db.addMahasiswa(new Mahasiswa("1608107010005", "Mariza"));

        //baca data mahasiswa
        Log.d("Reading: ", "Baca data mahasiswa");
        List<Mahasiswa> listMahasiswa = db.getAllMahasiswa();

        for (Mahasiswa mhs : listMahasiswa) {
            String log = "ID " + mhs.getId() + " ,NPM: " + mhs.getNim() + " ,Nama: " + mhs.getNama();
            Log.d("Semua Data Mahasiswa: ", log);
        }

        db.close();

    }
}
