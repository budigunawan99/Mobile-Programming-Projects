package id.ac.unsyiah.android.praktikum1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText edit_nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button2);
        edit_nama = findViewById(R.id.edit_nama);
   /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_nama.getText().toString();
                Toast.makeText(MainActivity.this, "Selamat Datang", Toast.LENGTH_SHORT).show();
            }
        });
    */
    }

    public void tekanTombol(View view) {
        String name = edit_nama.getText().toString();
        Toast.makeText(MainActivity.this, "Selamat Datang", Toast.LENGTH_SHORT).show();
    }
}
