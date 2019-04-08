package com.example.wydarzenia.settingsdata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.wydarzenia.model.User;

public class SettingsData {
    //Singleton to be used to fetch preferences set up in the settings activity
    //It uses SharedPreferences class that can store private data locally to be reused upon
    //opening the app again. VOLATILE means there will be no problem if thread switches DURING initialization
    private static volatile SettingsData settingsInstance;
    private Context context;
    private String PREFS;
    private SharedPreferences sharedPreferences;
    private String NIGHTMODE;
    private User user;

    //Private constructor as per singleton requirements. Constructor takes context as the function
    //getSharedPreferences has to come from context.
    private SettingsData(Context context){
        //Defense agains reflection
        if (settingsInstance != null){
            throw new RuntimeException("Use getInstance() to use this class");
        }
        //Setting token
        PREFS = "prefs";
        NIGHTMODE = "nightmode";
        //Saving context
        this.context = context;
        //Getting SharedPreferences instance
        sharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static SettingsData getInstance(Context context){
        if (settingsInstance == null) { //if there is no instance available... create new one
            //making it synchronized - now it is thread-safe
            synchronized (SettingsData.class) {
                System.out.println("creating=================================11111111111111");
                if (settingsInstance == null) settingsInstance = new SettingsData(context);
            }
        }
        return settingsInstance;
    }

    //Example method used to set one element of the settings:
    public void setNightmode(boolean enabled){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(NIGHTMODE, enabled);
        editor.apply();
        Toast.makeText(context, "Nightmode: " + String.valueOf(enabled), Toast.LENGTH_SHORT);
    }

    //Example method for loading given preference
    public boolean getNightmode(){
        return sharedPreferences.getBoolean(NIGHTMODE, false);
    }

    public User getUser() {
        System.out.println("getting user 222222222222222222222");
        System.out.println(user.toString());
        return user;
    }

    public void setUser(User user) {
        System.out.println("setting useerr xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        this.user = user;
    }


}
