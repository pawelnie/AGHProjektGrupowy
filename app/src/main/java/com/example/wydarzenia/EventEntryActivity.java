package com.example.wydarzenia;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wydarzenia.ViewModel.EventViewModel;
import com.example.wydarzenia.model.Event;
import com.example.wydarzenia.model.SignUp;
import com.example.wydarzenia.model.User;
import com.example.wydarzenia.network.GetDataService;
import com.example.wydarzenia.network.RetrofitClientInstance;
import com.example.wydarzenia.settingsdata.SettingsData;
import com.google.gson.internal.bind.SqlDateTypeAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventEntryActivity extends ActivityWithMenu {

    private final String EID_KEY = "eid";
    private final String LAT = "lat";
    private final String LON = "lon";
    private TextView eventTitle;
    private TextView eventDescription;
    private TextView eventInfo;
    private ImageView eventImage;
    private FloatingActionButton fab;
    private TextView countDown;
    private CountDownTimer countDownTimer;
    private Date startDate;
    private long timeLeft;


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
        countDown = (TextView) findViewById(R.id.countDownText);


        //initially hiding fab
        fab.hide();

        //Adding event class


        userId = SettingsData.getInstance(this).getUser().getId();

        eventId = Integer.parseInt(getIntent().getStringExtra(EID_KEY));

        //MAP
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EventMapFragment eventMapFragment = new EventMapFragment();
        Bundle bundle = new Bundle();
        //EO Map

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        eventViewModel.init(eventId);
        eventViewModel.getEventInfo().observe(this, event -> {
            eventTitle.setText(event.getTitle());
            eventDescription.setText(event.getDescription());
            eventInfo.setText(event.getDate());
            try{
                startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(event.getDate());
            }catch (ParseException e){
                Log.d("EventsApp", "Not the right format of the date");
            }
            startTimer();
            bundle.putDouble(LAT, Double.parseDouble(event.getLatitude()));
            bundle.putDouble(LON, Double.parseDouble(event.getLongitude()));
            eventMapFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.map_container, eventMapFragment);
            fragmentTransaction.commit();



        });

        checkIfSigned(userId);

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
//                responseId = Integer.parseInt(response.toString());
                Toast.makeText(EventEntryActivity.this, "Signed up!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                Toast.makeText(EventEntryActivity.this, "Sing up failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startTimer(){
        Date now = new Date();
        timeLeft = startDate.getTime() - now.getTime();
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void updateTimer(){
        int days = (int) (timeLeft / 86400000);
        int hours = (int) (timeLeft % 86400000 / 3600000);
        int minutes = (int) (timeLeft % 86400000 % 3600000 / 60000);
        int seconds = (int) (timeLeft % 86400000 % 3600000 % 60000 / 1000);
        String fullSentence = String.format("Remaining: %d days, %d hours, %d minutes and %d seconds",
            days, hours, minutes, seconds);
        countDown.setText(fullSentence);
    }

    private void checkIfSigned(int userId){
        Call<List<Event>> call = service.getUserEvents(userId);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                final List<Event> userEvents = response.body();
                boolean flag = true;
                for(Event event : userEvents){
                    if(event.getId() == eventId){
                        flag = false;
                    }
                }
                if(flag){
                    fab.show();
                }

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(EventEntryActivity.this, "Error checking if user is signed for event", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

