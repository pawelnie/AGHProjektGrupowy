package com.example.wydarzenia.model;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class EventCache {

    //to be implemented - PLACEHOLDER HERE
    //SHOULD IMPLEMENT SOME KIND OF LIST TO HOLD ENTITIES
    private static volatile EventCache eventCache;
    private HashMap<Integer, LiveData<Event>> events;
    public LiveData<Event> get(int eventId) {
        return events.get(eventId);
    }

    private EventCache(){
        //Defense agains reflection
        if (eventCache != null){
            throw new RuntimeException("Use getInstance() to use this class");
        }

        events = new HashMap<>();
    }

    public static EventCache getInstance(){
        if (eventCache == null) { //if there is no instance available... create new one
            //making it synchronized - now it is thread-safe
            synchronized (EventCache.class) {
                if (eventCache == null) eventCache = new EventCache();
            }
        }
        return eventCache;
    }

    public HashMap<Integer, LiveData<Event>> getCache(){
        return events;
    }

    public void addEvent(Event event) {
        final MutableLiveData<Event> data = new MutableLiveData<>();
        data.setValue(event);
        events.put(event.getId(), data);
    }


    //to be implemented
}
