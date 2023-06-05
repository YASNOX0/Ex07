package com.example.ex07.fragments;

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

import java.util.ArrayList;

public class SignUpFragment extends Fragment {

    private static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<User> getUsers(){
        return SignUpFragment.users;
    }

    EditText etSUp_firstName , etSUp_lastName , etSUp_email , etSUp_password , etSUp_confPassword;
    Button btnSUp_OK;


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //region Initializing attributes
        etSUp_firstName = view.findViewById(R.id.etSUp_firstName);
        etSUp_lastName = view.findViewById(R.id.etSUp_lastName);
        etSUp_email = view.findViewById(R.id.etSUp_email);
        etSUp_password = view.findViewById(R.id.etSUp_password);
        etSUp_confPassword = view.findViewById(R.id.etSUp_confPassword);
        btnSUp_OK = view.findViewById(R.id.btnSUp_OK);
        //endregion

        btnSUp_OK.setOnClickListener(view1 -> {
            if (etSUp_password.getText().toString().equals(etSUp_confPassword.getText().toString())) {
                try {
                    User user = new User(etSUp_firstName.getText().toString(),
                            etSUp_lastName.getText().toString(),
                            etSUp_email.getText().toString(),
                            etSUp_password.getText().toString());


                    //region Check and handle if the user exists
                    boolean isUserExists = false;
                    for (User listUser : users) {
                        if (user.getEmail().equalsIgnoreCase(listUser.getEmail())) {
                            isUserExists = true;
                            break;
                        }
                    }

                    if (isUserExists) {
                        Toast.makeText(getActivity(),"the user already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        users.add(user);
                        Toast.makeText(requireActivity(), "User Added", Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.fcv_activity_main
                                , SignInFragment.newInstance(user.getEmail(), user.getPassword())
                                ,null)
                                .commit();
                    }
                    //endregion

                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Confirm password is not the same as the entered password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}