package com.example.wydarzenia;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.wydarzenia.model.User;
import com.example.wydarzenia.settingsdata.SettingsData;

public class UserInfoActivity extends AppCompatActivity {
    private TextView usernameTV, firstnameTV, lastnameTV, emailTV, phoneTV, birthTV, homeTV;
    LiveData<User> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        user = SettingsData.getInstance(this).getUser();

        usernameTV = findViewById(R.id.usernameTVUI);
        firstnameTV = findViewById(R.id.firstnameTVUI);
        lastnameTV = findViewById(R.id.lastnameTVUI);
        emailTV = findViewById(R.id.emailTVUI);
        phoneTV = findViewById(R.id.phoneTVUI);
        birthTV = findViewById(R.id.birthTVUI);
        homeTV = findViewById(R.id.homeTVUI);

        //user = (User)getIntent().getSerializableExtra("User");

        usernameTV.setText(user.getValue().getUsername());
        firstnameTV.setText(user.getValue().getFirstname());
        lastnameTV.setText(user.getValue().getLastname());
        emailTV.setText(user.getValue().getEmail());
        phoneTV.setText(user.getValue().getPhone());
        birthTV.setText(user.getValue().getBirthdate());
        homeTV.setText(user.getValue().getHomelocation());
    }

}
