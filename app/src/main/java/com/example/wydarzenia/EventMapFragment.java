package com.example.wydarzenia;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wydarzenia.model.Event;
import com.example.wydarzenia.model.EventCache;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;

public class EventMapFragment extends Fragment implements OnMapReadyCallback {
    private final String LAT = "lat";
    private final String LON = "lon";
    private final String NEARBY_MODE = "nearbyMode";
    private GoogleMap mGoogleMap;
    private MapView mapView;
    private View view;
    private Double lat;
    private Double lon;
    private boolean nearbyMode;
    private boolean flag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        flag = false;
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            lat = bundle.getDouble(LAT);
            lon = bundle.getDouble(LON);
            nearbyMode = bundle.getBoolean(NEARBY_MODE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_event_map, container, false);
        mapView = (MapView) view.findViewById(R.id.map_embedded);
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mapView = (MapView) view.findViewById(R.id.map_embedded);

        if(mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if(!nearbyMode){
            googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("Event"));
        }
        CameraPosition set = CameraPosition.builder().target(new LatLng(lat, lon)).zoom(16)
                .bearing(0)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(set));
        flag = true;

        if (nearbyMode){
            List<Event> eventList = EventCache.getInstance().getSimpleList();
            for(Event event : eventList){
                Double latit = Double.parseDouble(event.getLatitude());
                Double longtit = Double.parseDouble(event.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(new LatLng(latit, longtit)).title(event.getTitle()));
            }
        }

    }


    public MapView getView(){
        return mapView;
    }

}
