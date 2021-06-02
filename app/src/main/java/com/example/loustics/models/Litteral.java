package com.example.loustics.models;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@Entity(tableName = "Litteral",
foreignKeys = {
    @ForeignKey(entity = Chapter.class, parentColumns = { "name", "courseName" }, childColumns = { "chapterName", "courseName" })
},
primaryKeys = {
    "subject", "chapterName", "courseName"
},
indices = {
    @Index("subject"),
    @Index("chapterName"),
    @Index("courseName")
})
public class Litteral extends Question {

    @NonNull
    @ColumnInfo(name = "subject")
    protected String m_s_subject;

    @NonNull
    @ColumnInfo (name = "chapterName")
    protected String m_s_chapterName;

    @NonNull
    @ColumnInfo (name = "courseName")
    protected String m_s_courseName;

    @ColumnInfo (name = "answers")
    protected JSONObject m_json_answers;


    public Litteral(String m_s_subject, String m_s_chapterName, String m_s_courseName, JSONObject m_json_answers) {
        this.m_s_subject = m_s_subject;
        this.m_s_chapterName = m_s_chapterName;
        this.m_s_courseName = m_s_courseName;
        this.m_json_answers = m_json_answers;
        try {
            m_json_rightAnswers = m_json_answers.getJSONArray("right");
            m_json_wrongAnswers = m_json_answers.getJSONArray("wrong");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View getAnswerView(Object answer, Context context) {
        TextView tv = new TextView(context);
        tv.setText(answer.toString());
        return tv;
    }

    public JSONObject getM_json_answers() {
        return this.m_json_answers;
    }

    public String getM_s_chapterName() {
        return this.m_s_chapterName;
    }

    public String getM_s_courseName() {
        return this.m_s_courseName;
    }

    public String getM_s_subject() {
        return this.m_s_subject;
    }

    @Override
    public View getSubjectView(Context context) {
        TextView tv = new TextView(context);
        tv.setText(getM_s_subject());
        return tv;
    }
}
