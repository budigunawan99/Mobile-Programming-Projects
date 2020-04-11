package id.ac.unsyiah.android.webservice.Model;

import com.google.gson.annotations.SerializedName;

public class MovieInfoResult extends DefaultResult{
    @SerializedName("data")
    private MovieInfo data;

    public MovieInfo getData() {
        return data;
    }

    public void setData(MovieInfo data) {
        this.data = data;
    }
}