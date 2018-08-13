package com.frb.books.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.frb.books.Service.HttpService;
import com.frb.books.classes.Error;
import com.frb.books.classes.PostBookResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URL;

public class PostRequestBooks extends AsyncTask<String,Void,String>{
    private HttpService httpService;
    private String strRequest;
    private Context appContext;
    private String codResult;

    public PostRequestBooks(String strRequest, Context appContext)
    {
        this.httpService = new HttpService();
        this.strRequest = strRequest;
        this.appContext = appContext;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
        URL url = new URL("https://ancient-earth-13943.herokuapp.com/api/books/");
        String response = httpService.HttpPost(url);
        String[] listResponse = response.split(":");
        if (listResponse[0].equals("400")) {
            String ErrorResponse = response.replace("400:", "");
            Error error = getError(ErrorResponse);
            codResult = listResponse[0];
            return error.getError();
        }
        System.out.println(response);
        PostBookResponse status = getRespons(response);
        codResult = "200";
        return status.toString();
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
    private PostBookResponse getRespons(String response)
    {
        Gson gson = new GsonBuilder().create();
        PostBookResponse postBookResponse = gson.fromJson(response,PostBookResponse.class);
        return postBookResponse;
    }

}
