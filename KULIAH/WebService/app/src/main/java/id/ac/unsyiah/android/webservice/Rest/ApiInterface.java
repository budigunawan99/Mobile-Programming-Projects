package id.ac.unsyiah.android.webservice.Rest;

import id.ac.unsyiah.android.webservice.Model.MovieInfoResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("movies/{titleId}")
    Call<MovieInfoResult> getMovieInfo(@Path("titleId") String titleId);
}
