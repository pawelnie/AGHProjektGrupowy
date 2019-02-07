package com.example.wydarzenia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameET, passwordET;
    private TextView statusTV;
    private String registerURL = "http://serpens.usermd.net/events/register.php"; //URL do skryptu php odpowiedzialnego za rejestrację (MySQL)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET = findViewById(R.id.newUsername);
        passwordET = findViewById(R.id.newPassword);
        statusTV = findViewById(R.id.statusReg);
    }


    public void buttonRegister(View view) {

        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        new SignIn(this, statusTV).execute(username, password, registerURL);

    }
}

