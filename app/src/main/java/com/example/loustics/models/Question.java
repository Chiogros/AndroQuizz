package com.example.loustics.models;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public abstract class Question {

    @Ignore
    protected JSONArray m_json_rightAnswers, m_json_wrongAnswers;


    public Question() {}

    public abstract boolean isRight(Object answer);

    public abstract View getAnswerView(Object answer, Context context);

    public JSONArray getM_json_rightAnswers() {
        return this.m_json_rightAnswers;
    }

    public JSONArray getM_json_wrongAnswers() {
        return this.m_json_wrongAnswers;
    }

    public String getRightAnswer() {

        int length = m_json_wrongAnswers.length();
        int randomValue = (int) ((Math.random() * 10 )% length);
        return m_json_rightAnswers .optString(randomValue);
    }

    public abstract View getSubjectView(Context context);

    public String getWrongAnswer() {
        int length = m_json_wrongAnswers.length();
        int randomValue = (int) ((Math.random() * 10 )% length);
        return m_json_wrongAnswers.optString(randomValue);
    }

}
