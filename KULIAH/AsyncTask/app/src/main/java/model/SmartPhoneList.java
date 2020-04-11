package model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SmartPhoneList {
    @SerializedName("products")
    private ArrayList<SmartPhone> smartPhone = new ArrayList<>();

    public ArrayList<SmartPhone> getSmartPhone() {
        return smartPhone;
    }

    public void setSmartPhone(ArrayList<SmartPhone> smartPhone) {
        this.smartPhone = smartPhone;
    }
}
