package id.ac.unsyiah.android.tugas3;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;

public class MainActivity extends AppCompatActivity {
    EditText edtNo, edtNama, edtJurusan, edtNpm;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView;
    DataMahasiswa dataMahasiswa;
    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChoose = (Button) findViewById(R.id.btnChoose);
        edtNo = findViewById(R.id.edt_no);
        edtNpm = findViewById(R.id.edt_npm);
        edtNama = findViewById(R.id.edt_nama);
        edtJurusan = findViewById(R.id.edt_jurusan);
        imageView = (ImageView) findViewById(R.id.imageView);
        dataMahasiswa = new DataMahasiswa(this);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void insertData() {
        Mahasiswa sp = new Mahasiswa();
        sp.setNo(Integer.parseInt(edtNo.getText().toString()));
        sp.setNpm(Long.parseLong(edtNpm.getText().toString()));
        sp.setNama(edtNama.getText().toString());
        sp.setJurusan(edtJurusan.getText().toString());
        sp.setImage(imageViewToByte(imageView));

        dataMahasiswa.buka();
        dataMahasiswa.addMahasiwa(sp);
        edtNo.setText("");
        edtNpm.setText("");
        edtNama.setText("");
        edtJurusan.setText("");
        imageView.setImageResource(R.mipmap.ic_launcher);
        dataMahasiswa.tutup();

        Toast.makeText(MainActivity.this, "Data berhasil disimpan", Toast.LENGTH_LONG).show();
    }

    public void simpan(View view) {
        int no = Integer.parseInt(edtNo.getText().toString());
        long npm = Long.parseLong(edtNpm.getText().toString());
        String nama = edtNama.getText().toString();
        String jurusan = edtJurusan.getText().toString();
        byte[] imageview = imageViewToByte(imageView);
        if (no == 0) {
            return;
        } else if (npm == 0){
            return;
        } else if (nama.isEmpty()) {
            return;
        } else if(jurusan.isEmpty()){
            return;
        }else if(imageview.length == 0){ return;
        }else {
            this.insertData();
        }
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @SuppressLint("DefaultLocale")
    public void btnGetAll(View view) {
        dataMahasiswa.buka();
        Mahasiswa[] listIdentitas = dataMahasiswa.getAllIdentitas();
        dataMahasiswa.tutup();
        if (listIdentitas!=null) {
            StringBuilder hasil = new StringBuilder();
            for (Mahasiswa identitas: listIdentitas) {
                hasil.append(String.format("No : %d, NPM : %d, Nama : %s, Jurusan : %s\n",
                        identitas.getNo(),
                        identitas.getNpm(),
                        identitas.getNama(),
                        identitas.getJurusan()));
            }
            Toast.makeText(this, hasil.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnGetByKode(View view) {
        String no = edtNo.getText().toString();
        dataMahasiswa.buka();
        Mahasiswa identitas = dataMahasiswa.getIdentitas(no);
        dataMahasiswa.tutup();
        if (identitas != null) {
            @SuppressLint("DefaultLocale") String hasil = String.format("No : %d, NPM : %d, Nama : %s, Jurusan : %s\n",
                    identitas.getNo(),
                    identitas.getNpm(),
                    identitas.getNama(),
                    identitas.getJurusan());
            edtNo.setText(String.valueOf(identitas.getNo()));
            edtNpm.setText(String.valueOf(identitas.getNpm()));
            edtNama.setText(identitas.getNama());
            edtJurusan.setText(identitas.getJurusan());

            byte[] pic = identitas.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(pic, 0, pic.length);
            imageView.setImageBitmap(bitmap);

            Toast.makeText(this, hasil, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnDelete(View view) {
        String no = edtNo.getText().toString();
        dataMahasiswa.buka();
        if (dataMahasiswa.deleteIdentitas(no)) {
            Toast.makeText(this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal dihapus", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnUpdate(View view) {
        Mahasiswa data = new Mahasiswa();
        data.setNo(Integer.parseInt(edtNo.getText().toString()));
        data.setNama(edtNama.getText().toString());
        data.setJurusan(edtJurusan.getText().toString());
        dataMahasiswa.buka();
        if (dataMahasiswa.updateIdentitas(data)) {
            Toast.makeText(this, "Berhasil Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal Update", Toast.LENGTH_SHORT).show();
        }
        dataMahasiswa.tutup();
    }

}
