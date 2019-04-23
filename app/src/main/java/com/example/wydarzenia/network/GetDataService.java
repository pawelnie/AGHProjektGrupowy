package com.example.wydarzenia.network;

import com.example.wydarzenia.model.Event;
import com.example.wydarzenia.model.SignUp;
import com.example.wydarzenia.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetDataService {

    /*Get Event with event.ID = "id"*/
    @GET("/event/{id}")
    Call<Event> getEvent(@Path("id") int id);

    /*Get all Events*/
    @GET("/event")
    Call<List<Event>> getAllEvents();

    @POST("/user")
    Call<User> saveUser(@Body User user);

    /*Get all Events for user with user.ID = "userid"*/
    @POST("/event/userevents/{userid}")
    Call<List<Event>> getUserEvents(@Path("userid") int userid);


    /*Get User info by his user.fireid*/
    @GET("/user/id/{fireid}")
    Call<List<User>> getUser(@Path("fireid") String fireid);

    @POST("/signup")
    Call<SignUp> signUpUser(@Body SignUp singUp);

    /*Not yet implemented*/
    @GET("/event/lat={lat}&lon={lon}")
    Call<List<Event>> getNearbyEvents(@Path("lat") Double lat, @Path("lon") Double lon);


}
