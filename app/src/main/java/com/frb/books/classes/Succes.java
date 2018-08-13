package com.frb.books.classes;

import com.google.gson.annotations.SerializedName;

public class Succes {
    @SerializedName("message")
    private String result;

    public Succes()
    {}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
