package com.example.ex07.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ex07.R;
import com.example.ex07.fragments.SignInFragment;
import com.example.ex07.fragments.SignUpFragment;

public class MainActivity extends AppCompatActivity implements SignInFragment.OnUserSignInListener{

    //region Initializing attributes
    SignInFragment signInFragment;
    SignUpFragment signUpFragment;
    Button btn_signIn, btn_signUp;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_signIn = findViewById(R.id.btn_signIn);
        btn_signUp = findViewById(R.id.btn_signUp);


        signInFragment = new SignInFragment();
        signUpFragment = new SignUpFragment();

        displayFragment(signInFragment);

    }

    //region Method : signInOrSignUp
    public void signInOrSignUp(View view) {
        if (view.equals(btn_signIn)) {
            displayFragment(signInFragment);
        } else {
            displayFragment(signUpFragment);
        }
    }
    //endregion

    //region Method : displayFragment
    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fcv_activity_main, fragment, null)
                .commit();
    }
    //endregion

    @Override
    public void onUserSignIn(String userFirstName, String userLastName) {
        Intent intent = new Intent(this, ConnectedActivity.class);
        intent.putExtra("firstName", userFirstName);
        intent.putExtra("lastName", userLastName);
        startActivity(intent);
    }
}
