package com.example.wydarzenia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameET, passwordET;
    private TextView statusTV;
    private String logInURL = "http://serpens.usermd.net/events/login.php"; //URL do skryptu php odpowiedzialnego za logowanie (MySQL)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = findViewById(R.id.usernameTE);
        passwordET = findViewById(R.id.passwordTE);
        statusTV = findViewById(R.id.status);
    }


    public void buttonLogin(View view) {
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        new SignIn(this, statusTV).execute(username, password, logInURL);
    }

    //Metoda ponizej pozwala przejsc do ekranu rejestracji
    public void goToReg(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    //Metoda ponizej pozwala przejsc do ekranu głównego aplikacji
    public void goHome(View view) {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

}

