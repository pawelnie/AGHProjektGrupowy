package com.example.wydarzenia;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wydarzenia.ViewModel.EventViewModel;





public class EventEntryActivity extends ActivityWithMenu {

    private final String EID_KEY = "eid";
    private TextView eventTitle;
    private TextView eventDescription;
    private TextView eventInfo;
    private ImageView eventImage;

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

        //Adding event class

        int eventId = Integer.parseInt(getIntent().getStringExtra(EID_KEY));

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        eventViewModel.init(eventId);
        eventViewModel.getEventInfo().observe(this, event -> {
            eventTitle.setText(event.getTitle());
            eventDescription.setText(event.getDescription());
            eventInfo.setText(event.getDate());
                });

    }
}

