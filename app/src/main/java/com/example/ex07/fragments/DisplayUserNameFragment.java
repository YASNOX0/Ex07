package com.example.ex07.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ex07.R;

public class DisplayUserNameFragment extends Fragment {

    //region Attributes
    private static final String ARG_FIRSTNAME = "firstName";
    private static final String ARG_LASTNAME = "lastName";
    private String firstName;
    private String lastName;
    Button btn_logOut;
    TextView tvLOut_fullName;
    //endregion

    public DisplayUserNameFragment() {
        // Required empty public constructor
    }


    public static DisplayUserNameFragment newInstance(String userFirstName, String userLastName) {
        DisplayUserNameFragment fragment = new DisplayUserNameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FIRSTNAME, userFirstName);
        args.putString(ARG_LASTNAME, userLastName);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.firstName = getArguments().getString(ARG_FIRSTNAME);
            this.lastName = getArguments().getString(ARG_LASTNAME);
        } else {
            this.firstName = "John";
            this.lastName = "Doe";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_user_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //region Initializing attributes
        btn_logOut = view.findViewById(R.id.btn_logOut);
        tvLOut_fullName = view.findViewById(R.id.tvLOut_fullName);
        //endregion

        tvLOut_fullName.setText(String.format("%s %s%s", firstName.toUpperCase(), lastName.substring(0, 1).toUpperCase(), lastName.substring(1).toLowerCase()));
        tvLOut_fullName.setTextColor(Color.BLUE);

        btn_logOut.setOnClickListener(view1 -> {
            requireActivity().finish();
        });
    }
}
