package com.bnawan.submission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.uncopt.android.widget.text.justify.JustifiedTextView;

public class DetailActivity extends AppCompatActivity {
    public static final String NAME_SHIO = "name_shio";
    public static final String DETAIL_SHIO = "detail_shio";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE_SHIO = "image_shio";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView namadetail = findViewById(R.id.namadetail);
        JustifiedTextView detaildetail = findViewById(R.id.detaildetail);
        TextView deskripsi = findViewById(R.id.deskripsidetail);
        ImageView imageDetail = findViewById(R.id.imageDetail);

        String nama = getIntent().getStringExtra(NAME_SHIO);
        String detail = getIntent().getStringExtra(DETAIL_SHIO);
        String description = getIntent().getStringExtra(DESCRIPTION);
        int image = getIntent().getIntExtra(IMAGE_SHIO, 0);

        setActionBarTitle(nama);
        namadetail.setText(nama);
        detaildetail.setText(detail);
        deskripsi.setText(description);
        imageDetail.setImageResource(image);
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
