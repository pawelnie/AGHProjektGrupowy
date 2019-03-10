package com.example.wydarzenia;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.wydarzenia.model.Event;

import javax.inject.Inject;

public class EventViewModel extends ViewModel {
    private LiveData<Event> event;
    private EventRepository eventRepository;

    //Using Dagger2 injects dependencies of repository
    @Inject
    public EventViewModel(EventRepository eventRepository){
        this.eventRepository = eventRepository;
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
