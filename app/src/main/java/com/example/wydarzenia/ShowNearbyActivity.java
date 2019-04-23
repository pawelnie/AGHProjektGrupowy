package com.example.wydarzenia;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wydarzenia.model.Event;
import com.example.wydarzenia.model.EventCache;
import com.example.wydarzenia.network.GetDataService;
import com.example.wydarzenia.network.RetrofitClientInstance;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowNearbyActivity extends AppCompatActivity {

    private final String LAT = "lat";
    private final String LON = "lon";
    private final String NEARBY_MODE = "nearbyMode";
    private Double lat;
    private Double lon;

    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_nearby);

        Bundle extras = getIntent().getExtras();
        lat = extras.getDouble(LAT);
        lon = extras.getDouble(LON);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EventMapFragment eventMapFragment = new EventMapFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(LAT, lat);
        bundle.putDouble(LON, lon);
        bundle.putBoolean(NEARBY_MODE, true);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Event>> call = service.getAllEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                eventList = response.body();

                EventCache.getInstance().setSimpleList(response.body());

                eventMapFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.map_container_show_nearby, eventMapFragment);
                fragmentTransaction.commit();


                for(Event event : response.body()){
                    EventCache.getInstance().addEvent(event);
                }

//                eventMapFragment.getView().getMapAsync(new OnMapReadyCallback() {
//                    @Override
//                    public void onMapReady(GoogleMap googleMap) {
//                        for(Event event : eventList){
//                            eventMapFragment.addPoint(Double.parseDouble(event.getLatitude()), Double.parseDouble(event.getLongitude()), event.getTitle());
//                        }
//                    }
//                });

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                if (t instanceof IOException) {

                    // logging probably not necessary
                }
                else {

                    // todo log to some central bug tracking service
                }
            }
        });

    }

}
