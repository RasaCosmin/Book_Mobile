package com.frb.books.classes;

import com.google.gson.annotations.SerializedName;

public class Error {
 @SerializedName("error")
    private String error;
    public Error()
    {}
    public String getError()
    {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
