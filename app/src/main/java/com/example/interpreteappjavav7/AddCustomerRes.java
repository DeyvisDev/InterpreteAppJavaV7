package com.example.interpreteappjavav7;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCustomerRes {

    @SerializedName("predd")
    @Expose
    private String predd;

    public String getPredd() {
        return predd;
    }

    public void setPredd(String predd) {
        this.predd = predd;
    }

}
