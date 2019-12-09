package com.example.snowtam_research;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Display_Snowtam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__snowtam);

        //Récupération des données de l'intent
        Intent intent = getIntent();
        String json_str = intent.getStringExtra("JSON");
        String oaci = intent.getStringExtra("OACI");
        String name = intent.getStringExtra("AEROPORT");

        //Le snowtam
        TextView jsonText = (TextView) findViewById(R.id.codedSnowtam);
        jsonText.setText(json_str);

        //Le code oaci
        TextView oaciText = (TextView) findViewById(R.id.codeAeroport2);
        oaciText.setText(oaci);

        //Le nom de l'aéroport
        TextView nameText = (TextView) findViewById(R.id.nomAeroport2);
        String str = getResources().getString(R.string.city);
        nameText.setText(str + " " + name);
    }

    //Ternmine l'activité, listener du bouton retour
    public void back(View view){
        finish();
    }
}
