package com.frb.books;


import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.frb.books.classes.Succes;
import com.frb.books.classes.User;
import com.frb.books.classes.Error;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements RegisterFragment.OnRegisterListener {
    private RegisterFragment registerFragment;
    private Gson gson;
    private String email;
    private String nume;
    private String password;
    private String confirmP;
    private android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        registerFragment = new RegisterFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.id_container, registerFragment);
        transaction.commit();


    }


    @Override
    public void onPressRegisterListener(String emailGet,String nameGet,String passwordGet, String confirmPasswordGet) {
       email = emailGet;
       nume = nameGet;
       password = passwordGet;
       confirmP = confirmPasswordGet;
        new SendPostRequest().execute();
    }

//    public boolean checkNetworkConnection()
//    {
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        boolean isConnected = false;
//        if (networkInfo != null && (isConnected = networkInfo.isConnected()))
//        {
//            co
//        }
//
//
//
//
//    }
    public class  SendPostRequest extends AsyncTask<String,Void,String>
    {
        String result = "";

        @Override
        protected void onPreExecute() { }

        @Override
        protected String doInBackground(String... strings) {
            try {
                return HttpPost();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        }
    }

    private Error getError(String response)
    {
        Gson gson = new GsonBuilder().create();
        Error error = gson.fromJson(response,Error.class);
        return error;
    }
    private Succes getSucces(String response)
    {
        Gson gson = new GsonBuilder().create();
        Succes succes = gson.fromJson(response,Succes.class);
        return succes;
    }
    private String convertUserToJson(User user)
    {
        Gson gson = new Gson();
        String jsonInStringUser = gson.toJson(user);
        return jsonInStringUser;
    }
    private String HttpPost() throws IOException
    {
        URL url = new URL("https://ancient-earth-13943.herokuapp.com/api/users/register");
        try{
            User user = new User();
            user.setEmail(email);
            user.setName(nume);
            user.setPassword(password);
            user.setConfirm(confirmP);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000/*milliseconds*/);
            conn.setConnectTimeout(15000 /*milliseconds*/);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type","application/json");

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            String str = convertUserToJson(user);
            writer.write(str);
            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            BufferedReader in;

            if (responseCode == HttpsURLConnection.HTTP_OK)
            {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line = "";
                while ((line = in.readLine())!=null)
                {
                    sb.append(line);
                    break;
                }
                Succes succes = getSucces(sb.toString());
                in.close();
                return succes.getResult();
            }
            else
            {
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                while ((line = in.readLine())!=null)
                {
                    sb.append(line);
                    break;
                }
                in.close();
                Error error = getError(sb.toString());
                return error.getError();
            }

        }
        catch (Exception e)
        {
            return new String("Exception: "+e.getMessage());
        }
    }



}
