package com.example.wydarzenia;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText emailET, passwordET;
    private ProgressBar progressBar;
    private Button loginButton;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createNotificationChannel();

        emailET = findViewById(R.id.usernameTE);
        passwordET = findViewById(R.id.passwordTE);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressLoginBar);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){


            startActivity(new Intent(getApplicationContext(),
                    MainActivity.class));
        }

    }


    public void buttonLogin(View view) {
        loginButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        String Email = emailET.getText().toString().trim();
        String Password = passwordET.getText().toString().trim();
        if (TextUtils.isEmpty(Email)){
            Toast.makeText(this, "Email Field is Empty", Toast.LENGTH_SHORT).show();
            resetVisibility();
            return;
        }
        if (TextUtils.isEmpty(Password)){
            Toast.makeText(this, "Password Field is Empty", Toast.LENGTH_SHORT).show();
            resetVisibility();
            return;
        }
        mAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        resetVisibility();
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

    //MM-> tylko do debuggowania mapy
    public void goToEvent(View view){
        Intent intent = new Intent(LoginActivity.this, ShowNearbyActivity.class);
        intent.putExtra("lat", 50.0646501);
        intent.putExtra("lon", 19.9449799);
        intent.putExtra("nearbyMode", true);
        startActivity(intent);
}

    private void resetVisibility(){
        loginButton.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "EventReminder";
            String description = "reminds of events";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("reminder", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}

