package com.example.snowtam_research;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

public class Map extends AppCompatActivity {
    private MapView mapview;
    private String lat, longitude;
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        lat = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");
        nom = intent.getStringExtra("aeroport");


        this.mapview = this.findViewById(R.id.mapView);
        this.mapview.onCreate(savedInstanceState);
        this.mapview.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                    }
                });

                CameraPosition newPosition = new CameraPosition.Builder()
                        .target(new LatLng(Float.parseFloat(lat), Float.parseFloat(longitude)))
                        .zoom(12)
                        .build();
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(newPosition), 10);
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng( Double.parseDouble(lat), Double.parseDouble(longitude)))
                        .title(nom));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapview.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapview.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapview.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapview.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapview.onDestroy();
    }

}

