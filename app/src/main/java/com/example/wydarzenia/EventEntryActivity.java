package com.example.wydarzenia;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wydarzenia.ViewModel.EventViewModel;


import dagger.android.AndroidInjection;


public class EventEntryActivity extends ActivityWithMenu {

    private final String EID_KEY = "eid";
    private TextView eventTitle;

    EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_entry);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        eventTitle = (TextView) findViewById(R.id.eventTitle);

        //Adding event class


//TODO: Pobieranie niepoprawny event, gdzies w okolicy lambdy jest blad
        int eventId = Integer.parseInt(getIntent().getStringExtra(EID_KEY));

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        eventViewModel.init(eventId);
        eventViewModel.getEventInfo().observe(this, event -> {
            eventTitle.setText(event.getTitle());
                });

    }
}

