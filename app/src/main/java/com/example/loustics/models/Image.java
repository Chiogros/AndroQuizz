package com.example.loustics.models;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.example.loustics.R;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "Image",
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
public class Image extends Question {

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


    public Image(String m_s_subject, String m_s_chapterName, String m_s_courseName, JSONObject m_json_answers) {
        this.m_s_subject = m_s_subject;
        this.m_s_chapterName = m_s_chapterName;
        this.m_s_courseName = m_s_courseName;
        this.m_json_answers = m_json_answers;
        try {
            m_json_rightAnswers = m_json_answers.getJSONArray("right");
            m_json_wrongAnswers = m_json_answers.getJSONArray("wrong");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("*****", "Impossible de parser correctement pour le sujet : " + m_s_subject);
        }
    }

    @Override
    public View getAnswerView(Object answer, Context context) {
        ImageView iv = new ImageView(context);
        iv.setImageResource(
                // à partir des resources
                context.getResources().getIdentifier(
                        // à partir du nom du logo
                        answer.toString(),
                        "drawable",
                        context.getPackageName()
                )
        );

        // iv.getLayoutParams().width = 8;

        return iv;
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
