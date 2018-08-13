package com.frb.books.Service;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpService {
    private String strRequest;
    private String token;
    private SharedPreferences preferences;
    private Context appContext;

    public HttpService(String strRequest) {
        this.strRequest = strRequest;
    }

    public HttpService(String strRequest,Context context) {
        this.strRequest = strRequest;
        this.appContext = context;
    }

    public HttpService() {
    }


    public String getStrRequest() {
        return strRequest;
    }

    public void setStrRequest(String strRequest) {
        this.strRequest = strRequest;
    }

    public String HttpPost(URL url) throws IOException
    {

        try{

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000/*milliseconds*/);
            conn.setConnectTimeout(15000 /*milliseconds*/);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type","application/json");
            preferences = appContext.getSharedPreferences("file",Context.MODE_PRIVATE);
            token = preferences.getString("token","0");
            if (!token.equals("0"))
            {
                conn.setRequestProperty("Authorization",token);
            }
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            //str = convertUserToJson(user);
            writer.write(strRequest);
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
                in.close();
                return sb.toString();
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
                return "400:"+sb.toString();

            }

        }
        catch (Exception e)
        {
            return new String("Exception: "+e.getMessage());
        }
    }

    public Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }
}
