package id.ac.unsyiah.android.praktikum3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static boolean mukaPertama = true;
    private static int diklik = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gantiMuka(View view) {
        String gambarDua = "a1";
        if (mukaPertama) {
            gambarDua = "a2";
        }
        mukaPertama = !mukaPertama;
        int res = getResources().getIdentifier(gambarDua, "drawable", getPackageName());
        ImageView im = findViewById(R.id.imgAhSheUp);
        im.setImageResource(res);
        diklik++;
        String pesan = String.format("You have punched Atta "+ diklik + " times");
        Toast.makeText(this,pesan,Toast.LENGTH_LONG).show();
    }
}
