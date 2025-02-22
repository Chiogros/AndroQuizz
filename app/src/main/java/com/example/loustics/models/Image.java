package com.example.loustics.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.example.loustics.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    private final String m_s_subject;

    @NonNull
    @ColumnInfo (name = "chapterName")
    private final String m_s_chapterName;

    @NonNull
    @ColumnInfo (name = "courseName")
    private final String m_s_courseName;

    @NonNull
    @ColumnInfo (name = "answers")
    private final JSONObject m_json_answers;

    @Ignore
    public static ArrayList<Class<? extends QuestionFrame>> QuestionsFramesCompatibleWith = new ArrayList<>();


    public Image(@NotNull String m_s_subject, @NotNull String m_s_chapterName, @NotNull String m_s_courseName, @NotNull JSONObject m_json_answers) {
        this.m_s_subject = m_s_subject;
        this.m_s_chapterName = m_s_chapterName;
        this.m_s_courseName = m_s_courseName;
        this.m_json_answers = m_json_answers;
        try {
            m_json_rightAnswers = this.m_json_answers.getJSONArray("right");
            m_json_wrongAnswers = this.m_json_answers.getJSONArray("wrong");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("*****", "Impossible de parser le sujet : '" + m_s_subject + "'");
        }
    }

    // Spécifie quels types de questions sont utilisables
    private static void fillQuestionsFramesCompatibles() {
        QuestionsFramesCompatibleWith.add(YesNo.class);
        QuestionsFramesCompatibleWith.add(CheckMCQ.class);
        QuestionsFramesCompatibleWith.add(RadioMCQ.class);
    }

    public boolean isCompatible(Class<? extends QuestionFrame> questionFrame) {
        if (QuestionsFramesCompatibleWith.size() == 0)
            fillQuestionsFramesCompatibles();

        return QuestionsFramesCompatibleWith.contains(questionFrame);
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

        return iv;
    }

    @NotNull
    public String getM_s_chapterName() {
        return this.m_s_chapterName;
    }

    @NotNull
    public String getM_s_courseName() {
        return this.m_s_courseName;
    }

    @NotNull
    public String getM_s_subject() {
        return this.m_s_subject;
    }

    @NotNull
    public JSONObject getM_json_answers() {
        return this.m_json_answers;
    }

    @Override
    public View getSubjectView(Context context) {
        TextView tv = new TextView(context);
        tv.setText(getM_s_subject());
        return tv;
    }
}
