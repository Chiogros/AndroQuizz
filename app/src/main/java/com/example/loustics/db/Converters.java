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
    public static String toJSONString(List<String> al_answers) {

        try {
            JSONArray jsonArr = new JSONArray(al_answers);
            Log.d("JSONStringFromArray", jsonArr.toString());
            return jsonArr.toString();
        } catch (Exception ex) {
            Log.e("Ooops", "yes");
        }
        return null;
    }

    @TypeConverter
    public static List<String> toArrayList(String answers) {

        try {
            JSONArray jsonArr = new JSONArray(answers);
        } catch (Exception ex) {
            Log.e("Ooops", "yes");
        }
        return new ArrayList<>();
    }

}
