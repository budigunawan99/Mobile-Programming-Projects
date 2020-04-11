package id.ac.unsyiah.android.webservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import id.ac.unsyiah.android.webservice.Model.MovieInfo;
import id.ac.unsyiah.android.webservice.Model.MovieInfoResult;
import id.ac.unsyiah.android.webservice.Rest.ApiClient;
import id.ac.unsyiah.android.webservice.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnGetInfo;
    private Button btnPost;
    private Button btnPut;
    private Button btnDelete;
    private EditText edtTitleGet;
    private EditText edtTitleId;
    private EditText edtTitleName;
    private EditText edtRating;
    private EditText edtDesc;
    private ImageView imgPoster;
    private ApiInterface apiModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetInfo = findViewById(R.id.btnGetInfo);
        btnPost = findViewById(R.id.btnPost);
        btnPut = findViewById(R.id.btnPut);
        btnDelete = findViewById(R.id.btnDelete);

        edtTitleGet = findViewById(R.id.edtTitleGet);
        edtTitleId = findViewById(R.id.edtTitleId);
        edtTitleName = findViewById(R.id.edtTitleName);
        edtRating = findViewById(R.id.edtRating);
        edtDesc = findViewById(R.id.edtDesc);
        imgPoster = findViewById(R.id.imgPoster);
        apiModel = ApiClient.getClient().create(ApiInterface.class);

        btnGetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovieInfo();
            }
        });
    }

    private void getMovieInfo() {
        String titleId = edtTitleGet.getText().toString();
        Call<MovieInfoResult> movieInfoCall = apiModel.getMovieInfo(titleId);
        movieInfoCall.enqueue(new Callback<MovieInfoResult>() {
            @Override
            public void onResponse(Call<MovieInfoResult> call, Response<MovieInfoResult> response) {
                if (response.body() != null) {
                    if (response.body().isSuccess()) {
                        MovieInfo info = response.body().getData();
                        edtTitleId.setText(info.getId());
                        edtTitleName.setText(info.getTitle());
                        edtRating.setText(String.valueOf(info.getRating()));
                        edtDesc.setText(info.getDescription());
                        String imageUrl = info.getImageUrl();
                        if (!imageUrl.equals("")) {
                            Picasso.get().load(imageUrl).error(R.drawable.no_image).into(imgPoster);
                        }else{
                            imgPoster.setImageResource(R.drawable.no_image);
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), response.body().getStatus(), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "unexpected response", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MovieInfoResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
