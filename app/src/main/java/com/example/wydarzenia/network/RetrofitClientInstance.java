package com.example.wydarzenia.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
//TODO -> Network
    private static final String BASE_URL = "http://10.0.2.2:8080";
    //www works with emulator and device: http://eventsapp.ovh
    //address only for emulator http://10.0.2.2:8080

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            //apiService = retrofit.create(GetDataService.class);
        }
        return retrofit;
    }

}
