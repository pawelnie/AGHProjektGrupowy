package com.example.wydarzenia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText emailET, passwordET;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.usernameTE);
        passwordET = findViewById(R.id.passwordTE);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),
                    MainActivity.class));
        }

    }


    public void buttonLogin(View view) {
        String Email = emailET.getText().toString().trim();
        String Password = passwordET.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            currentUser = mAuth.getCurrentUser();
                            finish();
                            startActivity(new Intent(getApplicationContext(),
                                    MainActivity.class));
                        }else {
                            Toast.makeText(LoginActivity.this, "couldn't login",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

