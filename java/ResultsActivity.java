package com.example.snowtam_research;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.ArrayList;

public class ResultsActivity extends PagerAdapter {

    private ArrayList<String> oaciList;
    private MapView mapView;
    private Context context;
    private Bundle savedInstanceState;
    private LayoutInflater inflater;
    private View view;
    private ArrayList<Snowtam> snowtam;
    private ArrayList<String> json_file;
    private ArrayList<Double> latitude, longitude;

    public ResultsActivity(Context context, Bundle savedInstanceState, ArrayList<String> oaci_list, ArrayList<String> json_file) {
        this.context = context;
        this.savedInstanceState = savedInstanceState;
        this.oaciList = oaci_list;
        this.json_file = json_file;
        this.snowtam = new ArrayList<Snowtam>();
        this.longitude = new ArrayList<Double>();
        this.latitude = new ArrayList<Double>();

        //Créations des Snowtam
        for(int i = 0; i < oaci_list.size(); i++){
            this.snowtam.add(new Snowtam(json_file.get(i), oaci_list.get(i), context));
        }

        Mapbox.getInstance(context, "pk.eyJ1IjoiY2VjaWxldGFsYXJtaW4iLCJhIjoiY2szOGpzN3Z3MDhjNzNobmNoZjkya2lxciJ9.OaBymLlD-Gr2vi_TBT_ucQ");

    }

    @Override
    public int getCount() {
        return oaciList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(R.layout.activity_results,container,false);

        //Récupération des éléments graphiques
        TextView text1 = (TextView) this.view.findViewById(R.id.codeAeroport);
        TextView textAeroport = (TextView) this.view.findViewById(R.id.nomAeroport);
        TextView timedate = (TextView) this.view.findViewById(R.id.date);

        //init du code oaci
        text1.setText(oaciList.get(position));
        //init du nom de l'aéroport
        String str = context.getResources().getString(R.string.city);
        textAeroport.setText(str + " " + snowtam.get(position).getAirport().getNom());
        //init de la datetime
        timedate.setText(snowtam.get(position).getTimedate());

        //Récupération des cooradonnées GPS
        this.latitude.add(position, Double.parseDouble(snowtam.get(position).getAirport().getLatitude()));
        this.longitude.add(position, Double.parseDouble(snowtam.get(position).getAirport().getLongitude()));
        //Configuration de la MapView
        mapView = this.view.findViewById(R.id.mapView);
        mapView.onCreate(this.savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {


                    }
                });
                 CameraPosition newPosition = new CameraPosition.Builder()
                        .target(new LatLng(Float.parseFloat(snowtam.get(position).getAirport().getLatitude()), Float.parseFloat(snowtam.get(position).getAirport().getLongitude())))
                        .zoom(10)
                        .build();
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(newPosition), 10);
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng( Double.parseDouble(snowtam.get(position).getAirport().getLatitude()), Double.parseDouble(snowtam.get(position).getAirport().getLongitude())))
                        .title(snowtam.get(position).getAirport().getNom()));
            }
        });

        container.addView(this.view);
        return this.view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

    //Convert sp value in px
    public static int spToPx(float sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    //Getters
    public Double getLongitudeResult(int i){ return this.longitude.get(i); }
    public Double getLatitudeResult(int i){ return this.latitude.get(i); }
    public String getNameAeropoort(int i){ return this.snowtam.get(i).getAirport().getNom(); }
    public String getJsonString(int i){ return this.json_file.get(i); }
}
