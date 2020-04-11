package id.ac.unsyiah.android.praktikum4;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editKode, editName, editJumlah;
    DataSupermarket dataSupermarket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editKode = findViewById(R.id.plKodeBarang);
        editName = findViewById(R.id.plNamaBarang);
        editJumlah = findViewById(R.id.plJumlah);
        dataSupermarket= new DataSupermarket(this);
    }

    public void insertData() {
        Supermarket sp = new Supermarket();
        sp.setKodeBarang(editKode.getText().toString());
        sp.setNamaBarang(editName.getText().toString());
        sp.setJumlah(Integer.parseInt(editJumlah.getText().toString()));
        dataSupermarket.buka();
        dataSupermarket.addBarang(sp);
        editKode.setText("");
        editName.setText("");
        editJumlah.setText("");
        dataSupermarket.tutup();

    }

    public void simpan(View view) {
        String nama = editKode.getText().toString();
        String kode=editName.getText().toString();
        String jumlah=editKode.getText().toString();
        if (nama.isEmpty()) {
           return;
        } else if (kode.isEmpty()){
            return;
        } else if (jumlah.isEmpty()) {
            return;
        } else {
            this.insertData();
        }
    }

    @SuppressLint("DefaultLocale")
    public void btnGetAll(View view) {
        dataSupermarket.buka();
        Supermarket[] listBarang = dataSupermarket.getAllBarang();
        dataSupermarket.tutup();
        if (listBarang!=null) {
            StringBuilder hasil = new StringBuilder();
            for (Supermarket barang: listBarang) {
                hasil.append(String.format("Kode=%s, Nama=%s, Jumlah=%d\n",
                        barang.getKodeBarang(),
                        barang.getNamaBarang(),
                        barang.getJumlah()));
            }
            Toast.makeText(this, hasil.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnGetByKode(View view) {
        String kode = editKode.getText().toString();
        dataSupermarket.buka();
        Supermarket barang = dataSupermarket.getBarang(kode);
        dataSupermarket.tutup();
        if (barang != null) {
            @SuppressLint("DefaultLocale") String hasil = String.format("Kode=%s, Nama=%s, Jumlah=%d\n",
                    barang.getKodeBarang(),
                    barang.getNamaBarang(),
                    barang.getJumlah());
            editKode.setText(barang.getKodeBarang());
            editName.setText(barang.getNamaBarang());
            editJumlah.setText(String.valueOf(barang.getJumlah()));
            Toast.makeText(this, hasil, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnDelete(View view) {
        String kode = editKode.getText().toString();
        dataSupermarket.buka();
        if (dataSupermarket.deleteBarang(kode)) {
            Toast.makeText(this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal dihapus", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnUpdate(View view) {
        Supermarket data = new Supermarket();
        data.setKodeBarang( editKode.getText().toString());
        data.setNamaBarang( editName.getText().toString());
        int jumlah = Integer.parseInt(editJumlah.getText().toString());
        data.setJumlah(jumlah);
        dataSupermarket.buka();
        if (dataSupermarket.updateBarang(data)) {
            Toast.makeText(this, "Berhasil Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal Update", Toast.LENGTH_SHORT).show();
        }
        dataSupermarket.tutup();
    }
}
