package com.example.loustics;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.Achievement;

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
        work();
    }

    private void getIntentValues() {
        m_s_courseName = getIntent().getStringExtra(COURSE);
        m_s_chapterName = getIntent().getStringExtra(CHAPTER);
        m_s_firstName = getIntent().getStringExtra(FIRSTNAME);
        m_s_lastName = getIntent().getStringExtra(LASTNAME);
        m_i_errors = getIntent().getIntExtra(ERRORS, 0);
        m_i_numberOfQuestions = getIntent().getIntExtra(NUMBEROFQUESTIONS, 0);
    }

    private void onFail() {
        // Dommage
        TextView message = findViewById(R.id.tv_result_message);
        message.setText(R.string.mmm);

        // Marque le score
        TextView score = findViewById(R.id.tv_score);
        String string = "Tu as réussi " + (m_i_numberOfQuestions - m_i_errors) + " question"
                + (m_i_errors < m_i_numberOfQuestions - 1 ? "s" : "") +
                " sur " + m_i_numberOfQuestions + ", mais ce n'est pas assez. Tu feras un meilleur score la prochaine fois !";
        score.setText(string);

        LinearLayout ll_action_bar = findViewById(R.id.action_bar);

        // Bouton Réessayer
        ImageView iv_tryAgain = new ImageView(getApplicationContext());
        iv_tryAgain.setImageResource(getResources().getIdentifier("ic_loop", "drawable", getPackageName()));
        iv_tryAgain.setColorFilter(getResources().getColor(R.color.defaultWhite));
        ll_action_bar.addView(iv_tryAgain);

        iv_tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ChaptersActivity.class);

                i.putExtra(ResultActivity.COURSE, m_s_courseName);
                i.putExtra(ResultActivity.CHAPTER, m_s_chapterName);
                i.putExtra(ResultActivity.FIRSTNAME, m_s_firstName);
                i.putExtra(ResultActivity.LASTNAME, m_s_lastName);

                startActivity(i);
                finish();
            }
        });

        // Espace
        Space space = new Space(getApplicationContext());
        space.setLayoutParams(new LinearLayout.LayoutParams(150, 1));
        ll_action_bar.addView(space);

        // Bouton Suivant
        ImageView iv_next = new ImageView(getApplicationContext());
        iv_next.setImageResource(getResources().getIdentifier("ic_forward", "drawable", getPackageName()));
        iv_next.setColorFilter(getResources().getColor(R.color.defaultWhite));
        ll_action_bar.addView(iv_next);

        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CoursesActivity.class);

                i.putExtra(ResultActivity.COURSE, m_s_courseName);
                i.putExtra(ResultActivity.CHAPTER, m_s_chapterName);
                i.putExtra(ResultActivity.FIRSTNAME, m_s_firstName);
                i.putExtra(ResultActivity.LASTNAME, m_s_lastName);

                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
                finish();
            }
        });
    }

    private void onSuccess() {
        // Enregistre dans la base de données
        Achievement newSuccess = new Achievement(m_s_chapterName, m_s_courseName, m_s_firstName, m_s_lastName);
        new AchievementsAsyncTask().execute(newSuccess);

        // Marque le score
        TextView score = findViewById(R.id.tv_score);
        String message = "Tu as réussi " + (m_i_numberOfQuestions - m_i_errors) + " questions sur " + m_i_numberOfQuestions + " !";
        score.setText(message);

        LinearLayout ll_action_bar = findViewById(R.id.action_bar);

        // Bouton Suivant
        ImageView iv_next = new ImageView(getApplicationContext());
        iv_next.setImageResource(getResources().getIdentifier("ic_forward", "drawable", getPackageName()));
        iv_next.setColorFilter(getResources().getColor(R.color.defaultWhite));
        ll_action_bar.addView(iv_next);

        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CoursesActivity.class);

                i.putExtra(ResultActivity.COURSE, m_s_courseName);
                i.putExtra(ResultActivity.CHAPTER, m_s_chapterName);
                i.putExtra(ResultActivity.FIRSTNAME, m_s_firstName);
                i.putExtra(ResultActivity.LASTNAME, m_s_lastName);

                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
                finish();
            }
        });
    }

    private void setDAOs() {
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    private void setNavigationBarColors() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }

    private boolean succededToTest() {
        return m_i_errors <= m_i_numberOfQuestions * 0.20;
    }

    private void work() {
        if (succededToTest()) {
            onSuccess();
        } else {
            onFail();
        }
    }

    // Classes privées

    private class AchievementsAsyncTask extends android.os.AsyncTask<Achievement, Void, String> {

        @Override
        protected String doInBackground(Achievement... achievements) {
            db.achievementDAO().insert(achievements[0]);

            return "Youhou, you succeded !";
        }
    }
}