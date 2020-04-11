package id.ac.unsyiah.android.logcat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tes = "Log Cat";

        int nilai = 10;
        String nilaiString = Integer.toString(nilai);

        Log.d("MainActivity","onCreate");
    }
}
