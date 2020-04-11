package id.ac.unsyiah.android.layoutwithjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ViewGroup buttonContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonContainer = findViewById(R.id.layoutCoba);
    }

    public void clickHandler(View view) {
        Button tombol = new Button(this);
        tombol.setText("Click me!");
        buttonContainer.addView(tombol);

    }
}
