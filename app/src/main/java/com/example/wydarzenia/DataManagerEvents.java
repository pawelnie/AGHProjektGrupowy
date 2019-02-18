package com.example.wydarzenia;

import java.util.ArrayList;
import java.util.List;

class DataManagerEvents {
    private static DataManagerEvents ourInstance = null;

    private List<EventInfo> mEvents = new ArrayList<>();

    static DataManagerEvents getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManagerEvents();
            ourInstance.initializeEvents();


        }
        return ourInstance;
    }

    private void initializeEvents() {
        mEvents.add(0, new EventInfo("U2 Concert"));
        mEvents.add(1, new EventInfo("Italian Cooking Event"));
        mEvents.add(2, new EventInfo("Football game"));
        mEvents.add(3, new EventInfo("Hobbit movie"));
        mEvents.add(4, new EventInfo("Krakow Marathon"));
        mEvents.add(5, new EventInfo("AGH Java Academy"));
        mEvents.add(6, new EventInfo("Swimming School"));
        mEvents.add(7, new EventInfo("Hobbit movie"));
        mEvents.add(8, new EventInfo("Krakow Marathon"));
        mEvents.add(9, new EventInfo("AGH Java Academy"));
        mEvents.add(10, new EventInfo("Swimming School"));

    }
    List<EventInfo> getEvents() {
        return mEvents;
    }

}
