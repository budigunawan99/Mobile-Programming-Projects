package id.ac.unsyiah.android.edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    TextView editNama;
    TextView editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNama = findViewById(R.id.editNama);
        editPassword = findViewById(R.id.editPassword);
    }

    public void clickHandler(View view) {
        String nama = editNama.getText().toString();
        String password = editPassword.getText().toString();
        String pesan = String.format("clickHandler: Nama = %s, Password=%s",nama,password );
//        Log.i(TAG, "clickHandler: Nama = " + nama + ", password= " + password);
        Toast.makeText(this,pesan,Toast.LENGTH_LONG).show();
    }
}
