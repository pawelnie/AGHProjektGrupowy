package com.example.wydarzenia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameET, passwordET;
    private TextView statusTV;
    private ProgressBar progressBar;
    private String logInURL = "https://serpens.usermd.net/events/login.php"; //server side LogIn script

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = findViewById(R.id.usernameTE);
        passwordET = findViewById(R.id.passwordTE);
        statusTV = findViewById(R.id.status);
        progressBar = findViewById(R.id.progressBar);
    }


    public void buttonLogin(View view) {
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        new SignIn(statusTV, progressBar).execute(username, password, logInURL);
    }

    //Metoda ponizej pozwala przejsc do ekranu rejestracji
    public void goToReg(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    //Metoda ponizej pozwala przejsc do ekranu głównego aplikacji
    public void goHome(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

}

