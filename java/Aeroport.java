package com.example.snowtam_research;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class Aeroport {
    private String id;
    private String nom;
    private String longitude;
    private String latitude;

    public Aeroport(String code, Context context){
        int i = 0;
        boolean find = false;
        //Parse list_airport.json to get data
        try {
            String json = this.loadJSONFromAsset(context);
            JSONArray reader = new JSONArray(json);
            while(!find && i < reader.length()) {
                JSONObject c = reader.getJSONObject(i);
                if(c.getString("code").equals(code)){
                    this.id = code;
                    this.nom = c.getString("name");
                    this.latitude = c.getString("latitude");
                    this.longitude = c.getString("longitude");
                    find = true;
                }
                i++;
            }if(!find){
                this.nom = "Not found";
            }
            Log.i("PARSER", this.nom);
        } catch (JSONException e) {
            e.printStackTrace();
            this.nom = "Not found";
        }

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLatitude() {
        return latitude;
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("list_airport.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
