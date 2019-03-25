package com.example.wydarzenia.network;

import com.example.wydarzenia.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/event")
    Call<List<Event>> getAllEvent();

//    TODO
//    below for connecting with first server application, to be removed
//@GET("/blog")
//Call<List<Event>> getAllEvent();

    @GET("/event/1")
    Call<Event> getFirst();
}
