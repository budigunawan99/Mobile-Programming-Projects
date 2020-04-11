package id.ac.unsyiah.android.newactivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    protected String namaMataKuliah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        namaMataKuliah = getIntent().getStringExtra(MainActivity.NAMA_MATA_KULIAH);
        TextView tv = findViewById(R.id.tvDetail);
        tv.setText(namaMataKuliah);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnMainActivity(View view) {
        getIntent().putExtra("kirimBalik", "Anda sedang mengambil Mtkul " + namaMataKuliah);
        setResult(RESULT_OK, getIntent());
        finish();
    }

    public void websiteClickHandler(View view) {
        Uri webPage = Uri.parse("http://unsyiah.ac.id");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
        startActivity(webIntent);
    }
}
