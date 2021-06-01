package com.example.loustics.db;

import android.util.Log;

import androidx.room.TypeConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public static String toString(JSONObject json) {
        return json.toString();
    }

    @TypeConverter
    public static JSONObject toJSONObject(String jsonString) throws JSONException {
        return new JSONObject(jsonString);
    }

}
