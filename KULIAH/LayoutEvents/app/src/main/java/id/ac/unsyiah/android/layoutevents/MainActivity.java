package id.ac.unsyiah.android.layoutevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tombol2 = findViewById(R.id.tombol);
        tombol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick");
            }
        });
    }

    public void registerClickHandler(View view) {
        Button tombol = (Button) view;
        Log.d("MainActivity", "registerClickHandler " +tombol.getText());
    }
}
