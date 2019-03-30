package com.example.wydarzenia.network;

import com.example.wydarzenia.model.Event;
import com.example.wydarzenia.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("/event")
    Call<List<Event>> getAllEvent();

//    TODO
//    below for connecting with first server application, to be removed
//@GET("/blog")
//Call<List<Event>> getAllEvent();

    @GET("/event/1")
    Call<Event> getFirst();

    @GET("/event/{id}")
    Call<Event> getEvent(@Path("id") int id);

    @POST("/user")
    Call<User> saveUser(@Body User user);

}
