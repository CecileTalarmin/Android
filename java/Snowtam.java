package com.example.snowtam_research;

import android.content.Context;
import android.util.Log;

public class Snowtam {

    private String json_str;
    private String oaci_code;
    private String timedate;
    private Aeroport airport;

    //for logging
    private static String TAG = "PARSER";

    Snowtam(String json, String oaci, Context context){
        this.airport = new Aeroport(oaci, context);
        this.json_str = json;
        this.oaci_code = oaci;

        String[] json_splitted = json_str.split("\n");

        //field B
        if(json_str.equals(context.getResources().getString(R.string.noSnowtamMsg))) {
            timedate = context.getResources().getString(R.string.noTimeDate);
        } else {
            String dateToParse = json_splitted[3].substring(3,11); //ex: 11270246
            String[] date_array = splitStringEvery(dateToParse, 2);
            String date = date_array[1] + "/" + date_array[0];
            String hour = date_array[2] + "h" + date_array[3];
            this.timedate = date + " " + hour;
        }
        Log.i(TAG, timedate);

    }

    //function to split a string each interval
    private String[] splitStringEvery(String s, int interval) {
        int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
        String[] result = new String[arrayLength];

        int j = 0;
        int lastIndex = result.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            result[i] = s.substring(j, j + interval);
            j += interval;
        } //Add the last bit
        result[lastIndex] = s.substring(j);

        return result;
    }

    public Aeroport getAirport(){
        return this.airport;
    }

    public String getTimedate(){
        return this.timedate;
    }
}
