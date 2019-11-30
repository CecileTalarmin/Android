package com.example.snowtam_research;

import android.util.Log;

public class Snowtam {

    private String json_str;
    private String oaci_code;
    private String timedate;
    private static String TAG = "PARSER";

    Snowtam(String json, String oaci){
        this.json_str = json;
        this.oaci_code = oaci;

        String[] json_splitted = json_str.split("\n");
        for(int i = 0; i < json_splitted.length; i++) {
            Log.i(TAG,json_splitted[i]);
        }

        //field A
        //oaci_code = json_splitted[2].substring(3);
        //Log.i(TAG,  oaci_code);

        //field B
        String dateToParse = json_splitted[3].substring(3,11); //ex: 11270246
        String[] date_array = splitStringEvery(dateToParse, 2);
        String date = date_array[1] + "/" + date_array[0];
        String hour = date_array[2] + "h" + date_array[3];
        this.timedate = date + " " + hour;
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
}
