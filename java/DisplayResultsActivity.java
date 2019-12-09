package com.example.snowtam_research;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.ArrayList;

public class DisplayResultsActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ResultsActivity myadapter;
    private ArrayList<String> oaciList;
    private ArrayList<String> jsonList;
    private ArrayList<Double> latitude;
    private ArrayList<Double> longitude;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        oaciList = extras.getStringArrayList("OACI_LIST");
        jsonList = extras.getStringArrayList("JSON_LIST");


        viewPager = findViewById(R.id.viewpager);
        myadapter = new ResultsActivity(this, savedInstanceState, oaciList, jsonList);
        viewPager.setAdapter(myadapter);

        tabLayout = this.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);

        //dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);

    }


    public void agrandirMap(View view){
        //Toast.makeText(this,"Carte cliquée", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DisplayResultsActivity.this, Map.class);
        MapView mapView = this.findViewById(R.id.mapView);


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                    }
                });

                CameraPosition currentCameraPosition = mapboxMap.getCameraPosition();
                //latitude = currentCameraPosition.target.getLatitude();
                //longitude = currentCameraPosition.target.getLongitude();


            }
        });
        intent.putExtra("latitude", this.myadapter.getLatitudeResult(this.viewPager.getCurrentItem()).toString());
        intent.putExtra("longitude", this.myadapter.getLongitudeResult(this.viewPager.getCurrentItem()).toString());
        intent.putExtra("aeroport", this.myadapter.getNameAeropoort(this.viewPager.getCurrentItem()));
        startActivity(intent);

    }

    //Lance l'activité d'affichage du Snowtam sous la forme codé
    public void showCodedSnowtam(View view){

        Intent intent = new Intent(DisplayResultsActivity.this, Display_Snowtam.class);

        intent.putExtra("OACI", oaciList.get(this.viewPager.getCurrentItem()));
        intent.putExtra("AEROPORT", this.myadapter.getNameAeropoort(this.viewPager.getCurrentItem()));
        intent.putExtra("JSON", this.myadapter.getJsonString(this.viewPager.getCurrentItem()));
        startActivity(intent);

    }



}
