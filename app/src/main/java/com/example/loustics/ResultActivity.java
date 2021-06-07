package com.example.loustics;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.Achievement;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    public static final String CHAPTER = "chapterName";
    private String m_s_chapterName;
    public static final String COURSE = "courseName";
    private String m_s_courseName;
    public static final String LASTNAME = "lastName";
    private String m_s_lastName;
    public static final String FIRSTNAME = "firstName";
    private String m_s_firstName;
    public static final String ERRORS = "errors";
    private int m_i_errors;
    public static final String NUMBEROFQUESTIONS = "numberOfQuestions";
    private int m_i_numberOfQuestions;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getIntentValues();
        setDAOs();
        setNavigationBarColors();
        calculateSuccess();
    }

    // Enregistre dans la base de données si on a réussi
    private void calculateSuccess() {
        if (m_i_errors <= m_i_numberOfQuestions * 20) {
            Achievement newSuccess = new Achievement(m_s_chapterName, m_s_courseName, m_s_firstName, m_s_lastName);
            new AchievementsAsyncTask().execute(newSuccess);
        }
    }

    public void getIntentValues() {
        m_s_courseName = getIntent().getStringExtra(COURSE);
        m_s_chapterName = getIntent().getStringExtra(CHAPTER);
        m_s_firstName = getIntent().getStringExtra(FIRSTNAME);
        m_s_lastName = getIntent().getStringExtra(LASTNAME);
        m_i_errors = getIntent().getIntExtra(ERRORS, 0);
        m_i_numberOfQuestions = getIntent().getIntExtra(NUMBEROFQUESTIONS, 0);
    }

    public void setDAOs() {
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    public void setNavigationBarColors() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }


    // Classes privées

    private class AchievementsAsyncTask extends android.os.AsyncTask<Achievement, Void, String> {

        @Override
        protected String doInBackground(Achievement... achievements) {
            db.achievementDAO().insert(achievements[0]);

            String message = "Youhou, you succeded !";
            return message;
        }
    }
}