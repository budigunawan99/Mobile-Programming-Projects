package id.ac.unsyiah.android.stringresource;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView haloTeks = findViewById(R.id.haloTeks);
        String halo = String.format(getString(R.string.hallo_bambang), "Halo Hari ini cerah sekali");
        haloTeks.setText(halo);

        TextView loremIpsum = findViewById(R.id.lorem_ipsum);
        loremIpsum.setMovementMethod(new ScrollingMovementMethod());
    }

}
