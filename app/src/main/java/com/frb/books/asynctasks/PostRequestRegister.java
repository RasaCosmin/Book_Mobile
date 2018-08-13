package com.frb.books.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.frb.books.MainActivity;
import com.frb.books.Service.HttpService;
import com.frb.books.classes.Error;
import com.frb.books.classes.Succes;
import com.frb.books.classes.loginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URL;

    public class  PostRequestRegister extends AsyncTask<String,Void,String> {
        String result = "";
        private HttpService httpService;
        private String strRequest;
        private Context appContext;
        private String codResult;

        public PostRequestRegister( String strRequest, Context appContext) {
            this.httpService = new HttpService();
            this.strRequest = strRequest;
            this.appContext = appContext;
        }

        @Override
        protected void onPreExecute() {
            httpService.setStrRequest(strRequest);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("https://ancient-earth-13943.herokuapp.com/api/users/register");
                httpService.setStrRequest(strRequest);
                String response = httpService.HttpPost(url);
                String[] listResponse = response.split(":");
                if (listResponse[0].equals("400")) {
                    String ErrorResponse = response.replace("400:", "");
                    Error error = getError(ErrorResponse);
                    codResult = listResponse[0];
                    return error.getError();
                }
                Succes succes = getSucces(response);
                codResult = "200";
                //System.out.println(succes.getResult());
                return succes.getResult();

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

        private Succes getSucces(String response) {
            Gson gson = new GsonBuilder().create();
            Succes succes = gson.fromJson(response, Succes.class);
            return succes;
        }

        public String getCodResult() {
            return codResult;
        }
    }

