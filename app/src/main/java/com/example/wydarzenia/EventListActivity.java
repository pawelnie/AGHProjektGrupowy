package com.example.wydarzenia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    private static RecyclerView.Adapter mAdapter;
    private static RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<EventData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        myOnClickListener = new MyOnClickListener(this);

        //for debug purpose simple dataset:
        data = new ArrayList<EventData>();
        data.add(new EventData("name123", "brief", "content", 1, 1));
        data.add(new EventData("name2", "brief2", "content2", 2, 2));
        data.add(new EventData("name3", "brief3", "content3", 3, 3));

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        //Used to improve performance when we know the recycler view does not change diameters
        mRecyclerView.setHasFixedSize(true);

        //Linear layout manager.
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //Setting up an adapter
        mAdapter = new CardAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

    }

    private class MyOnClickListener implements View.OnClickListener{

        private final Context context;

        private MyOnClickListener(Context context){
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedItemPosition = mRecyclerView.getChildAdapterPosition(v);
            EventData selectedEvent = data.get(selectedItemPosition);
            Intent intent = new Intent(context, EventEntryActivity.class);
            intent.putExtra("ID", selectedEvent.getId());
            context.startActivity(intent);
        }

    }
}
