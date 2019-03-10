package com.example.wydarzenia;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class EventEntryActivity extends ActivityWithMenu {

    private final String EID_KEY = "eid";
    private EventViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Adding event class
        int userId = Integer.parseInt(getIntent().getStringExtra(EID_KEY));
        viewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        viewModel.init(userId);
        viewModel.getEventInfo().observe(this, event -> {
            //UPDATE UI
                });


    }
}

