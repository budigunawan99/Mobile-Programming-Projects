package id.ac.unsyiah.android.webservice.Model;

import com.google.gson.annotations.SerializedName;

public class DefaultResult {
    @SerializedName("status")
    private String status;

    public String getStatus(){
        return status;
    }
    public boolean isSuccess(){
        return status.equals("success");
    }
}
