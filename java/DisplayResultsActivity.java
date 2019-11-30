package com.example.snowtam_research;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayResultsActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ResultsActivity myadapter;
    private ArrayList<String> oaciList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        oaciList = extras.getStringArrayList("OACI_LIST");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myadapter = new ResultsActivity(this, savedInstanceState, oaciList);
        viewPager.setAdapter(myadapter);

    }

    public void chargerPiste(View view){
        Toast.makeText(this, "Bouton cliqué", Toast.LENGTH_LONG).show();
    }


}
