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


public class RegisterFragment extends Fragment {

    private TextInputLayout emailInput;
    private TextInputLayout nameInput;
    private TextInputLayout passwordInput;
    private TextInputLayout confirmInput;
    private Button buttonLogin;
    private OnRegisterListener listener;

    public RegisterFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment,container,false);
        emailInput = view.findViewById(R.id.id_email);
        nameInput = view.findViewById(R.id.id_nume);
        passwordInput = view.findViewById(R.id.id_password);
        buttonLogin = view.findViewById(R.id.id_register);
        confirmInput = view.findViewById(R.id.id_confirmPassword);
        return view;
    }

    @Override
    public void onResume() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nume = nameInput.getEditText().getText().toString();
                String password = passwordInput.getEditText().getText().toString();
                String email = emailInput.getEditText().getText().toString();
                String confirmPassword = confirmInput.getEditText().getText().toString();
                listener.onPressRegisterListener(email,nume,password,confirmPassword);
            }
        });

        super.onResume();

    }

    public interface OnRegisterListener
    {
        public void onPressRegisterListener(String email, String name, String password, String confirmPassword);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnRegisterListener)context;
    }
}
