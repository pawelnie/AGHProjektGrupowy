package com.example.wydarzenia;

import java.util.ArrayList;
import java.util.List;

public class Dane {
    private static Dane ourInstance = null;

    List<EventInfo> mEvents = new ArrayList<>();

    public static Dane getInstance() {
        if(ourInstance == null) {
            ourInstance = new Dane();
            ourInstance.initializeEvents();


        }
        return ourInstance;
    }

    private void initializeEvents() {
        mEvents.add(0, new EventInfo("Event1"));
        mEvents.add(1, new EventInfo("Event2"));
        mEvents.add(2, new EventInfo("Event2"));
        mEvents.add(3, new EventInfo("Event2"));
        mEvents.add(4, new EventInfo("Event2"));
        mEvents.add(5, new EventInfo("Event2"));
        mEvents.add(6, new EventInfo("Event2"));

    }
    public List<EventInfo> getEvents() {
        return mEvents;
    }

}
