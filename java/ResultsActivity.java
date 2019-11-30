package com.example.snowtam_research;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.ArrayList;

public class ResultsActivity extends PagerAdapter {

    private ArrayList<String> oaciList;
    private TextView text1;
    private MapView mapView;
    private Context context;
    private Bundle savedInstanceState;
    private LayoutInflater inflater;
    private View view;

    public ResultsActivity(Context context, Bundle savedInstanceState, ArrayList<String> oaci_list) {
        this.context = context;
        this.savedInstanceState = savedInstanceState;
        this.oaciList = oaci_list;

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
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(R.layout.activity_results,container,false);
        text1 = (TextView) view.findViewById(R.id.nomAeroport);
        text1.setText(oaciList.get(position));

        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(this.savedInstanceState);
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

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
