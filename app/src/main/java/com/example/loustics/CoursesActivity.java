package com.example.loustics;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.CourseDAO;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.Achievement;
import com.example.loustics.models.Chapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {

    public static final String COURSE = "courseName";
    private String m_s_courseName;
    public static final String LASTNAME = "lastName";
    private String m_s_lastName;
    public static final String FIRSTNAME = "firstName";
    private String m_s_firstName;
    private AppDatabase db;
    private List<String> m_l_achievedChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        m_l_achievedChapters = new ArrayList<>();

        getIntentValues();
        setDAOs();
        setHeader();
        setNavigationBarColors();

        new AchievementsAsyncTask().execute();
    }

    private void getIntentValues() {
        // Nom de la matière
        m_s_courseName = getIntent().getStringExtra(COURSE);
        m_s_firstName = getIntent().getStringExtra(FIRSTNAME);
        m_s_lastName = getIntent().getStringExtra(LASTNAME);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    private void setDAOs() {
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    // Nom de la matière dans l'en-tête
    private void setHeader() {

        // on applique le texte au TextView afin qu'il soit affiché
        TextView t = (TextView) findViewById(R.id.tv_course);
        t.setText(m_s_courseName);

        // Charger le logo correspondant à la matière
        new LogoAsyncTask().execute(m_s_courseName);
    }

    private void setImageViewLogo(String resources) {
        ImageView iv = (ImageView) findViewById(R.id.iv_course);
        iv.setImageResource(
                getResources().getIdentifier(
                        // à partir du nom du logo
                        resources,
                        "drawable",
                        getPackageName()
                ));
        // change la couleur du logo en blanc
        iv.setColorFilter(getResources().getColor(R.color.defaultWhite), PorterDuff.Mode.SRC_IN);
    }

    private void setListView(@NotNull List<Chapter> chapters) {

        // Définition des lignes pour le ListView + l'action du click sur chaque ligne qui renvoit sur une nouvelle activité
        ListView lv_items = findViewById(R.id.lv_items);
        lv_items.setAdapter(new ArrayAdapter<Chapter>(getApplicationContext(), R.id.lv_items, chapters) {
            /*
            méthode utilisée à la génération de la ListView, renvoie une View qui est affichée dans la liste
             */
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout ll_line = new LinearLayout(getContext());
                ll_line.setPadding(0, 30, 0, 30);
                ll_line.setOrientation(LinearLayout.HORIZONTAL);
                ll_line.setGravity(Gravity.CENTER_VERTICAL);

                // Le dernier enfant doit forcément être le nom de la matière
                // Nom de la matière
                TextView tv_chapterName = new TextView(getContext());
                tv_chapterName.setText(this.getItem(position).getM_s_name());
                tv_chapterName.setTextSize(25);
                ll_line.addView(tv_chapterName);
                
                // Au clic sur le chapitre
                ll_line.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LinearLayout ll_line = (LinearLayout) view;
                        TextView tv_chapterName = (TextView) ((LinearLayout) view).getChildAt(0);

                        Intent i = new Intent(getContext(), ChaptersActivity.class);

                        i.putExtra(ChaptersActivity.COURSE, m_s_courseName);
                        i.putExtra(ChaptersActivity.CHAPTER, tv_chapterName.getText());
                        i.putExtra(ChaptersActivity.FIRSTNAME, m_s_firstName);
                        i.putExtra(ChaptersActivity.LASTNAME, m_s_lastName);

                        startActivity(i);
                    }
                });

                if (m_l_achievedChapters.contains(this.getItem(position).getM_s_name())) {
                    // écarte le logo du text
                    Space space = new Space(getContext());
                    space.setLayoutParams(new LinearLayout.LayoutParams(25, 1));
                    ll_line.addView(space);

                    ImageView checked = new ImageView(getContext());
                    checked.setImageResource(getResources().getIdentifier("ic_check","drawable", getPackageName()));
                    checked.setColorFilter(getResources().getColor(R.color.green));
                    ll_line.addView(checked);
                }

                return ll_line;
            }
        });
    }

    // afficher la navigationBar en blanc avec les boutons noirs
    private void setNavigationBarColors() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }


    // Classes privées

    private class ChaptersAsyncTask extends android.os.AsyncTask<String, Void, List<Chapter>> {
        @Override
        protected List<Chapter> doInBackground(String... strings) {
            return db.chapterDAO().getAllChapters(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Chapter> chapters) {
            setListView(chapters);
        }
    }

    private class LogoAsyncTask extends android.os.AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            CourseDAO courseDAO = db.courseDAO();
            return courseDAO.getLogo(strings[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            setImageViewLogo(result);
        }
    }

    private class AchievementsAsyncTask extends android.os.AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... voids) {
            ArrayList<String> achievedChapters = new ArrayList<>();

            List<Achievement> achieved = db.achievementDAO().getAllAchievementsInACourse(m_s_firstName, m_s_lastName, m_s_courseName);
            for (Achievement a : achieved) {
                achievedChapters.add(a.getM_s_chapterName());
            }

            return achievedChapters;
        }

        @Override
        protected void onPostExecute(List<String> chaptersNames) {
            m_l_achievedChapters = chaptersNames;
            new ChaptersAsyncTask().execute(m_s_courseName);
        }
    }
}
