package com.example.wydarzenia.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.wydarzenia.model.User;
import com.example.wydarzenia.network.GetDataService;
import com.example.wydarzenia.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private GetDataService dataService;

//    private LiveData<List<Event>> allEvents;
//
//    public EventRepository(Application application){
//
//    }

    public LiveData<User> getUser(String fireId){
//        LiveData<User> cached = UserCache.getInstance().get(eventId);
//        if(cached != null){
//            System.out.println("using cache");
//            return cached;
//        }

        final MutableLiveData<User> data = new MutableLiveData<>();
        dataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        dataService.getUser(fireId).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> userCall, Response<List<User>> response) {
                //setting the data to the response
                data.setValue(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

        return data;
    }
}
