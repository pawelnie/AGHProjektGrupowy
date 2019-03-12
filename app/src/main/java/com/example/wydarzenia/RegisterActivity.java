package com.example.wydarzenia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameET, passwordET;
    private TextView statusTV;
    private ProgressBar progressBar;
    private String registerURL = "https://serpens.usermd.net/events/register.php"; //server side SgnUp script

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET = findViewById(R.id.newUsername);
        passwordET = findViewById(R.id.newPassword);
        statusTV = findViewById(R.id.statusReg);
        progressBar = findViewById(R.id.progressBarReg);
    }


    public void buttonRegister(View view) {

        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        new SignIn(statusTV, progressBar).execute(username, password, registerURL);

    }
}

