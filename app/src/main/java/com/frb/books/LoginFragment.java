package com.frb.books;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.w3c.dom.Text;

public class LoginFragment extends Fragment {

    private TextInputLayout email_input;
    private TextInputLayout password_input;
    private Button login;
    private String email;
    private String password;
    private onLoginListener listener;

    public LoginFragment()
    { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login,container,false);

        email_input = (TextInputLayout)v.findViewById(R.id.email_id);
        password_input = (TextInputLayout)v.findViewById(R.id.password_id);
        login = (Button) v.findViewById(R.id.login_id);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = email_input.getEditText().getText().toString();
                password = password_input.getEditText().getText().toString();
                listener.onPressLogin(email,password);
            }
        });
    }

    public interface onLoginListener
    {
        public void onPressLogin(String email,String password);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (onLoginListener)context;

    }
}
