package com.frb.books;


import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.frb.books.Service.HttpService;
import com.frb.books.asynctasks.PostRequestBooks;
import com.frb.books.asynctasks.PostRequestLogin;
import com.frb.books.asynctasks.PostRequestRegister;

import com.frb.books.classes.Book;
import com.frb.books.classes.Succes;
import com.frb.books.classes.User;
import com.frb.books.classes.Error;
import com.frb.books.classes.loginResponse;
import com.frb.books.classes.loginUser;
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
import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity implements RegisterFragment.OnRegisterListener {
    private RegisterFragment registerFragment;
    private LoginFragment loginFragment;
    private Gson gson;
    private String email;
    private String nume;
    private String password;
    private String confirmP;
    private android.support.v4.app.FragmentManager fragmentManager;
    private String strRequest;
    private int id_metoda;
    private  Handler handler;
    private Book book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler =new Handler();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        registerFragment = new RegisterFragment();
        loginFragment = new LoginFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.id_container, registerFragment);
        transaction.commit();
//        book = new Book();
//        book.setAuthor("Liviu Rebreanu");
//        book.setPublisher("Adevarul");
//        book.setTitle("Proza");
//        String request = convertBookToJson(book);
//        new PostRequestBooks(request,getApplicationContext()).execute();
    }


    @Override
    public void onPressRegisterListener(String emailGet,String nameGet,String passwordGet, String confirmPasswordGet) {
       email = emailGet;
       nume = nameGet;
       password = passwordGet;
       confirmP = confirmPasswordGet;
       id_metoda = 1;
       if (password.equals(confirmP)) {

           User user = new User();
           user.setEmail(email);
           user.setName(nume);
           user.setPassword(password);
           user.setConfirm(confirmP);
           strRequest = convertUserToJson(user);
           final PostRequestRegister postRequestRegister = new PostRequestRegister(strRequest,getApplicationContext());
           postRequestRegister.execute();
           //System.out.println(postRequestRegister.getCodResult());

               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {

                       if (!(postRequestRegister.getCodResult().equals("400"))) {
                           FragmentTransaction transaction = fragmentManager.beginTransaction();
                           transaction.replace(R.id.id_container, loginFragment);
                           transaction.commit();
                       }
                   }
               }, 5000);

       }
       else
       {
           Toast.makeText(this,"Passwords don't match!",Toast.LENGTH_SHORT).show();
       }
    }





    private String convertUserToJson(User user)
    {
        Gson gson = new Gson();
        String jsonInStringUser = gson.toJson(user);
        return jsonInStringUser;
    }
    private String convertLoginUserToJson(loginUser user)
    {
        Gson gson = new Gson();
        String jsonInStringUser = gson.toJson(user);
        return jsonInStringUser;
    }
    private String convertBookToJson(Book book)
    {
        Gson gson = new Gson();
        String jsonInStringBook = gson.toJson(book);
        return jsonInStringBook;
    }




}
