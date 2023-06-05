package com.example.ex07.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex07.fragments.DisplayUserNameFragment;
import com.example.ex07.R;
import com.example.ex07.fragments.SignInFragment;

public class ConnectedActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);

        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");

        DisplayUserNameFragment fragment = DisplayUserNameFragment.newInstance(firstName, lastName);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        moveTaskToBack(true);
    }
}
