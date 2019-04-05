package com.example.wydarzenia;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wydarzenia.ViewModel.EventViewModel;
import com.example.wydarzenia.model.SignUp;
import com.example.wydarzenia.model.User;
import com.example.wydarzenia.network.GetDataService;
import com.example.wydarzenia.network.RetrofitClientInstance;
import com.example.wydarzenia.settingsdata.SettingsData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventEntryActivity extends ActivityWithMenu {

    private final String EID_KEY = "eid";
    private TextView eventTitle;
    private TextView eventDescription;
    private TextView eventInfo;
    private ImageView eventImage;
    private FloatingActionButton fab;
    int eventId;
    Integer responseId;
    int userId;

    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

    EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_entry);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        eventTitle = (TextView) findViewById(R.id.eventTitle);
        eventDescription = (TextView) findViewById(R.id.eventDescription);
        eventInfo = (TextView) findViewById(R.id.eventInfo);
        eventImage = (ImageView) findViewById(R.id.eventImage);
        fab = (FloatingActionButton) findViewById(R.id.fabEvent);

        //Adding event class

        userId = SettingsData.getInstance(this).getUser().getValue().getId();

        eventId = Integer.parseInt(getIntent().getStringExtra(EID_KEY));

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        eventViewModel.init(eventId);
        eventViewModel.getEventInfo().observe(this, event -> {
            eventTitle.setText(event.getTitle());
            eventDescription.setText(event.getDescription());
            eventInfo.setText(event.getDate());
                });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUpUser(userId, eventId);
            }
        });

    }

    private void signUpUser(int userId, int eventId){
        service.signUpUser(new SignUp(userId,
                eventId)).enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                responseId = Integer.parseInt(response.toString());
                Toast.makeText(EventEntryActivity.this, "Signed up!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                Toast.makeText(EventEntryActivity.this, "Sing up failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

