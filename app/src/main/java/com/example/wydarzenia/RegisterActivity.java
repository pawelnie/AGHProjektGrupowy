package com.example.wydarzenia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wydarzenia.model.User;
import com.example.wydarzenia.network.GetDataService;
import com.example.wydarzenia.network.RetrofitClientInstance;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailET, passwordET, usernameET, nameET, lastnameET, homeET, birthET, phoneET,
    birthMonthET, birthYearET;
    private Button registerButton;
    private ProgressBar progressBar;
    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    String responseStr;
    private final String REG = "reg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailET = findViewById(R.id.newUserEmail);
        passwordET = findViewById(R.id.newPassword);
        usernameET = findViewById(R.id.newUsername);
        nameET = findViewById(R.id.newFirstname);
        lastnameET = findViewById(R.id.newLastname);
        homeET = findViewById(R.id.newHomelocation);

        birthET = findViewById(R.id.newBirthdate);
        birthMonthET = findViewById(R.id.newBirthMonth);
        birthYearET = findViewById(R.id.newBirthYear);

        phoneET = findViewById(R.id.newPhoneNumber);
        registerButton = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressRegBar);

        mAuth = FirebaseAuth.getInstance();
    }


    public void buttonRegister(View view) {
        registerButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        String birthDateFull = birthYearET.getText().toString().trim() + "-" +
                birthMonthET.getText().toString().trim() + "-" +
                birthET.getText().toString().trim();

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
        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            //check if successful
                            if (task.isSuccessful()) {
                                //User is successfully registered and logged in
                                sendUser(mAuth.getCurrentUser().getUid(),
                                        lastnameET.getText().toString().trim(),
                                        nameET.getText().toString().trim(),
                                        usernameET.getText().toString().trim(),

                                        /*birthET.getText().toString().trim(),*/
                                        birthDateFull,

                                        homeET.getText().toString().trim(),
                                        Email,
                                        phoneET.getText().toString().trim());

                                //start Profile Activity here
                                Toast.makeText(RegisterActivity.this, "registration successful",
                                        Toast.LENGTH_SHORT).show();
                                resetVisibility();
                                finish();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this, "Couldn't register, try again",
                                        Toast.LENGTH_SHORT).show();
                                resetVisibility();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            resetVisibility();
                        }
                    }
                });
    }

    public void sendUser(String fireid, String lastname, String name, String username, String birth, String home, String email, String phone) {
        service.saveUser(new User(fireid, lastname, name, username, birth, home, email, phone)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(REG, "Response: "+response.toString());
                responseStr = response.toString();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(REG, "onFailure:" + t.toString());
            }
        });
    }

    private void resetVisibility(){
        registerButton.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}

