package com.example.loustics.db;

import android.util.Log;

import androidx.room.TypeConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Converters {
    // tranforme le JSON en String pour la BDD
    @TypeConverter
    public static String toString(JSONObject json) {
        return json.toString();
    }

    // transforme le String de la BDD e JSON
    @TypeConverter
    public static JSONObject toJSONObject(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

}
