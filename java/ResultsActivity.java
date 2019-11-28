package com.example.snowtam_research;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import androidx.annotation.NonNull;

import android.view.View;
import android.widget.Toast;


public class ResultsActivity extends AppCompatActivity {

    private ArrayList<String> oaciList;
    private TextView text1, text2, text3, text4;
    private String oaci1, oaci2, oaci3, oaci4;
    private MapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiY2VjaWxldGFsYXJtaW4iLCJhIjoiY2szOGpzN3Z3MDhjNzNobmNoZjkya2lxciJ9.OaBymLlD-Gr2vi_TBT_ucQ");
        setContentView(R.layout.activity_results);

        text1 = (TextView) findViewById(R.id.nomAeroport);
        /*text2 = (TextView) findViewById(R.id.code2);
        text3 = (TextView) findViewById(R.id.code3);
        text4 = (TextView) findViewById(R.id.code4);*/

        Intent intent = getIntent();

        oaci1 = intent.getStringExtra("OACI_1");
        oaci2 = intent.getStringExtra("OACI_2");
        oaci3 = intent.getStringExtra("OACI_3");
        oaci4 = intent.getStringExtra("OACI_4");

        if(!oaci1.isEmpty())
            text1.setText(oaci1);
        if(!oaci2.isEmpty())
            text2.setText(oaci2);
        if(!oaci3.isEmpty())
            text3.setText(oaci3);
        if(!oaci4.isEmpty())
            text4.setText(oaci4);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {


                    }
                });
            }
        });
    }

    // Add the mapView lifecycle to the activity's lifecycle methods
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    public void chargerPiste(View view){
        Toast.makeText(this, "Bouton cliqué", Toast.LENGTH_LONG).show();
    }
}
