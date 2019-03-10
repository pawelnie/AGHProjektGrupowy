package com.example.wydarzenia;

import com.example.wydarzenia.model.Event;

import java.util.ArrayList;
import java.util.List;

class DataManagerEvents {
    private static DataManagerEvents ourInstance = null;

    private List<Event> mEvents = new ArrayList<>();

    static DataManagerEvents getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManagerEvents();
            ourInstance.initializeEvents();


        }
        return ourInstance;
    }

    private void initializeEvents() {
        mEvents.add(0, new Event(1, "U2 Concert", "desc"));
        mEvents.add(1, new Event(2, "Italian Cooking Event", "desc"));
        mEvents.add(2, new Event(3, "Football game", "desc"));
        mEvents.add(3, new Event(4, "Hobbit movie", "desc"));
        mEvents.add(4, new Event(5, "Krakow Marathon", "desc"));
        mEvents.add(5, new Event(6, "AGH Java Academy", "desc"));
        mEvents.add(6, new Event(7, "Swimming School", "desc"));
        mEvents.add(7, new Event(8, "Hobbit movie", "desc"));
        mEvents.add(8, new Event(9, "Krakow Marathon", "desc"));
        mEvents.add(9, new Event(10, "AGH Java Academy", "desc"));
        mEvents.add(10, new Event(11, "Swimming School", "desc"));

    }
    List<Event> getEvents() {
        return mEvents;
    }

}
