package com.example.wydarzenia.model;
import android.arch.lifecycle.LiveData;
import java.util.List;


public class EventCache {

    //to be implemented - PLACEHOLDER HERE
    //SHOULD IMPLEMENT SOME KIND OF LIST TO HOLD ENTITIES
    public List<LiveData<Event>> events;
    public LiveData<Event> get(int eventId) {
        return events.get(eventId);
    }


    //to be implemented
}
