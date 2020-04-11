package id.ac.unsyiah.android.imageview;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tombolGantiGambar(View view) {
        String namaGambar = "logo_unsyiah_2";
        int res = getResources().getIdentifier(namaGambar, "drawable", getPackageName());

        ImageView iv = findViewById(R.id.logoUnsyiah);
        iv.setImageResource(res);
    }

    public void gambarDariAsset(View view) {
        String namaGambar = "gambar_notes.png";
        ImageView iv = findViewById(R.id.logoUnsyiah);

        try {
            InputStream stream = getAssets().open(namaGambar);
            Drawable drawable = Drawable.createFromStream(stream, null);
            iv.setImageDrawable(drawable);
        } catch (Exception e) {
            Log.e("Error image", e.getMessage());
        }
    }
}
