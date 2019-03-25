package com.example.wydarzenia.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.wydarzenia.model.Event;


public class EventViewModel extends AndroidViewModel {
    private LiveData<Event> event;
    private EventRepository eventRepository;

    public EventViewModel(Application application){
        super(application);
        this.eventRepository = new EventRepository();
    }

    public void init(int eventId){
        if(this.event != null){
            //event id does not change
            return;
        }
        event = eventRepository.getEvent(eventId);
    }

    public LiveData<Event> getEventInfo(){
        return event;
    }
}
