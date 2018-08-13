package com.frb.books.classes;


import com.google.gson.annotations.SerializedName;

public class loginResponse {
    @SerializedName("success")
    private boolean success;
    @SerializedName("token")
    private String token;

    public loginResponse() {
    }

    public boolean isSucces() {
        return success;
    }

    public void setSucces(boolean succes) {
        this.success = succes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
