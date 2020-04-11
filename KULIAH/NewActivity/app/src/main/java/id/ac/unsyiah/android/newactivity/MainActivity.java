package id.ac.unsyiah.android.newactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String NAMA_MATA_KULIAH = "nama mata kuliah";
    public static final int DETAIL_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnDetailActivity(View view) {
        Intent detailIntent = new Intent(this, DetailActivity.class);

        MataKuliah mtkul = new MataKuliah();
        detailIntent.putExtra(NAMA_MATA_KULIAH, mtkul.getNamaMtkul());

        startActivityForResult(detailIntent, DETAIL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DETAIL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String msg = data.getStringExtra("kirimBalik");
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            }
        }
    }
}
