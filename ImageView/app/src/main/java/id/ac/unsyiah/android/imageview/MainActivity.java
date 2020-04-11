package id.ac.unsyiah.android.imageview;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView loremIpsum = findViewById(R.id.tvDescription);
        loremIpsum.setMovementMethod(new ScrollingMovementMethod());
    }

    public void tombolGantiGambar(View view) {
        final Button button = (Button)findViewById(R.id.tombol2);
        String namaGambar = "logo_unsyiah_2";
        int res = getResources().getIdentifier(namaGambar, "drawable", getPackageName());

        String namaGambar1 = "logo_unsyiah";
        int res1 = getResources().getIdentifier(namaGambar1,"drawable", getPackageName());

        if (button.getText()=="Ganti Gambar"){
            button.setText("Kembalikan");
            ImageView img =(ImageView) findViewById(R.id.logoUnsyiah);
            img.setImageResource(res);
        }else {
            button.setText("Ganti Gambar");
            ImageView img = (ImageView) findViewById(R.id.logoUnsyiah);
            img.setImageResource(res1);
        }

     //   ImageView iv = findViewById(R.id.logoUnsyiah);
     //   iv.setImageResource(res);
    }

    public void gambarDariAsset(View view) {
        final Button tombool = (Button)findViewById(R.id.tombool);
        String namaGambar = "gambar_notes.png";

        String namaGambar1 = "logo_unsyiah";
        int res1 = getResources().getIdentifier(namaGambar1,"drawable", getPackageName());

        ImageView iv = findViewById(R.id.logoUnsyiah);

        try {
            InputStream stream = getAssets().open(namaGambar);

            if (tombool.getText()=="Gambar Dari Asset"){
                tombool.setText("Kembalikan");
                Drawable drawable = Drawable.createFromStream(stream, null);
                iv.setImageDrawable(drawable);
            }else {
                tombool.setText("Gambar Dari Asset");
                iv.setImageResource(res1);
            }
            // Drawable drawable = Drawable.createFromStream(stream, null);
            // iv.setImageDrawable(drawable);
        } catch (Exception e) {
            Log.e("Error image", e.getMessage());
        }
    }
}
