package id.ac.unsyiah.android.tugas1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.Configuration;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    TextView editNama;
    TextView editNpm;
    TextView editProdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNama = findViewById(R.id.editNama);
        editNpm = findViewById(R.id.editNpm);
        editProdi = findViewById(R.id.editProdi);
    }

    public void clickHandler(View view) {
        String nama = editNama.getText().toString();
        String npm = editNpm.getText().toString();
        String prodi = editProdi.getText().toString();
        String pesan = String.format("Nama = %s, Npm = %s,  Prodi = %s",nama,npm,prodi);
//        Log.i(TAG, "clickHandler: Nama = " + nama + ", password= " + password);
        Toast.makeText(this,pesan,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
