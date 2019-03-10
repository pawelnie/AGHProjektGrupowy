package com.example.wydarzenia.network;

import com.example.wydarzenia.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/blog")
    Call<List<Event>> getAllBlogs();

    @GET("/blog/1")
    Call<Event> getFirst();
}
