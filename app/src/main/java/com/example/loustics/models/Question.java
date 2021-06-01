package com.example.loustics.models;

import android.content.Context;
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


@Entity(tableName = "Question",
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
public abstract class Question {

    @NonNull
    @ColumnInfo(name = "subject")
    private String m_s_subject;

    @NonNull
    @ColumnInfo (name = "chapterName")
    private String m_s_chapterName;

    @NonNull
    @ColumnInfo (name = "courseName")
    private String m_s_courseName;

    @ColumnInfo (name = "answers")
    private JSONObject m_json_answers;

    @Ignore
    private JSONArray m_json_rightAnswers, m_json_wrongAnswers;
    @Ignore
    private Context m_c_context;

    public Question(String m_s_subject, String m_s_chapterName, String m_s_courseName, JSONObject m_json_answers) {
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


    public abstract View getAnswerView(Object answer);

    public Context getM_c_context() {
        return this.m_c_context;
    }

    public JSONObject getM_json_answers() {
        return this.m_json_answers;
    }

    public JSONArray getM_json_rightAnswers() {
        return this.m_json_rightAnswers;
    }

    public JSONArray getM_json_wrongAnswers() {
        return this.m_json_wrongAnswers;
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

    public String getRightAnswer() {
        int length = m_json_wrongAnswers.length();
        int randomValue = (int) (Math.random() * length);
        return m_json_rightAnswers .optString(randomValue);
    }

    public abstract View getSubjectView();

    public String getWrongAnswer() {
        int length = m_json_wrongAnswers.length();
        int randomValue = (int) (Math.random() * length);
        return m_json_wrongAnswers.optString(randomValue);
    }

}
