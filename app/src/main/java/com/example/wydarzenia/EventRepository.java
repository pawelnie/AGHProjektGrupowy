package com.example.wydarzenia;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.wydarzenia.model.Event;
import com.example.wydarzenia.model.EventCache;
import com.example.wydarzenia.network.GetDataService;

import java.util.List;


import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Informs Dagger that this class should be constructed only once.
@Singleton
public class EventRepository {

    private GetDataService dataService;

    //future to implement
    private EventCache eventCache;

    public LiveData<Event> getEvent(int eventId){
        LiveData<Event> cached = eventCache.get(eventId);
        if(cached != null){
            return cached;
        }

        final MutableLiveData<Event> data = new MutableLiveData<>();
        dataService.getFirst().enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                //setting the data to the response
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {

            }
        });
        return data;
    }
}
