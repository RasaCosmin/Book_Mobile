package com.frb.books.asynctasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.frb.books.Service.HttpService;
import com.frb.books.classes.Error;
import com.frb.books.classes.Succes;
import com.frb.books.classes.loginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URL;

public class PostRequestLogin extends AsyncTask<String,Void,String>{
    private HttpService httpService;
    private String strRequest;
    private Context appContext;
    private String codResult;
    private SharedPreferences sharedPreferences;


    public PostRequestLogin(String strRequest, Context appContext) {
        this.httpService = new HttpService();
        this.strRequest = strRequest;
        this.appContext = appContext;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
        URL url = new URL("https://ancient-earth-13943.herokuapp.com/api/users/login");
        String response = httpService.HttpPost(url);
        String[] listResponse = response.split(":");
        if (listResponse[0].equals("400")) {
            System.out.println(response);
            String ErrorResponse = response.replace("400:", "");
            Error error = getError(ErrorResponse);
            codResult = listResponse[0];
            return error.getError();
        }
        loginResponse loginResponse = getLoginResponse(response);
        codResult = "200";
        //System.out.println(succes.getResult());

        if (loginResponse.isSucces())
        {
            sharedPreferences = appContext.getSharedPreferences("file", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("token",loginResponse.getToken());
            return loginResponse.getToken();
        }
        else
        {
            return  "ERROR";
        }


    } catch (IOException e) {
        e.printStackTrace();
    }
            return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(appContext, s, Toast.LENGTH_LONG).show();
    }
    private Error getError(String response) {
        Gson gson = new GsonBuilder().create();
        Error error = gson.fromJson(response, Error.class);
        return error;
    }
    private loginResponse getLoginResponse(String response)
    {
        Gson gson = new GsonBuilder().create();
        loginResponse loginResponse = gson.fromJson(response, com.frb.books.classes.loginResponse.class);
        return loginResponse;
    }
}
