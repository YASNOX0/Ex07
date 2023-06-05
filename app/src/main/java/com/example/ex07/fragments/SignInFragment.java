package com.example.ex07.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ex07.R;
import com.example.ex07.dataClasses.User;

public class SignInFragment extends Fragment {

    //region Attributes
    private static final String ARG_EMAIL = "email";
    private static final String ARG_PASSWORD = "password";
    private String email;
    private String password;
    EditText etSIn_email, etSIn_password;
    Button btnSIn_OK;
    private String userFirstName;
    private String userLastName;
    OnUserSignInListener parentAcitvity;
    //endregion

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance(String email, String password) {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EMAIL, email);
        args.putString(ARG_PASSWORD, password);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.email = getArguments().getString(ARG_EMAIL);
            this.password = getArguments().getString(ARG_PASSWORD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSIn_email = view.findViewById(R.id.etSIn_email);
        etSIn_password = view.findViewById(R.id.etSIn_password);
        btnSIn_OK = view.findViewById(R.id.btnSIn_OK);

        etSIn_email.setText(email);
        etSIn_password.setText(password);


        btnSIn_OK.setOnClickListener(view1 -> {
            boolean isEmailCorrect = false;
            boolean isPasswordCorrect = false;
            for (User user : SignUpFragment.getUsers()) {
                if (etSIn_email.getText().toString().equalsIgnoreCase(user.getEmail())) {
                    isEmailCorrect = true;
                    if(etSIn_password.getText().toString().equalsIgnoreCase(user.getPassword())){
                        isPasswordCorrect =true;
                        userFirstName = user.getFirstName();
                        userLastName = user.getLastName();
                        break;
                    }
                    break;
                }
            }
            if (isEmailCorrect) {
                if(isPasswordCorrect){
                    parentAcitvity.onUserSignIn(userFirstName, userLastName);
                }
                else {
                    Toast.makeText(getActivity(), "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            } else{
                Toast.makeText(getActivity(), "The user doesn't exist", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public interface OnUserSignInListener {
        void onUserSignIn(String userFirstName, String userLastName);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            parentAcitvity = (OnUserSignInListener) getActivity();
        } catch (ClassCastException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}


