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
    /*Get Events with ID = "id"*/
    @GET("/event/{id}")
    Call<Event> getEvent(@Path("id") int id);

    /*Get all Events*/
    @GET("/event")
    Call<List<Event>> getAllEvent();

    @POST("/user")
    Call<User> saveUser(@Body User user);

    /*Get Events with ID = "id"*/
    @POST("/event/userevents/{userid}")
    Call<List<Event>> getUserEvents(@Path("userid") int userid);


    /*Get User info by his fireid*/
    @GET("/id/{fireid}")
    Call<User> getUser(@Path("fireid") String fireid);

}
