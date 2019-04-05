package com.example.wydarzenia.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.wydarzenia.model.User;

public class UserViewModel extends AndroidViewModel {
    private LiveData<User> user;
    private UserRepository userRepository;

    public UserViewModel(Application application){
        super(application);
        this.userRepository = new UserRepository();
    }

    public void init(String fireId){
        if(this.user != null){
            //event id does not change
            return;
        }
        user = userRepository.getUser(fireId);
    }

    public LiveData<User> getUserInfo(){
        return user;
    }
}
