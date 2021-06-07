package com.example.loustics;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.CourseDAO;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private AppDatabase db;
    private CourseDAO courseDAO;
    public static final String LASTNAME = "";
    private String m_s_lastName;
    public static final String FIRSTNAME = "";
    private String m_s_firstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getIntentValues();
        setHelloText();
        setupDAOs();
        setNavigationBarColors();
        setFloatingButton();
        fetchCourses();
    }

    private void setHelloText() {
        TextView t = findViewById(R.id.tv_bonjour);

        String helloText = t.getText().toString();
        helloText += " " + m_s_firstName;
        t.setText(helloText);
    }

    private void fetchCourses() {
        new CoursesAsyncTask().execute();
    }

    public void getIntentValues() {
        // Nom et prénom de l'utilisateur
        m_s_lastName = getIntent().getStringExtra(LASTNAME);
        m_s_firstName = getIntent().getStringExtra(FIRSTNAME);
    }

    public void setFloatingButton() {
        FloatingActionButton fab = findViewById(R.id.fab_settings);
        fab.setImageResource(R.drawable.ic_settings);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i);
            }
        });
    }

    public void setListView(@NotNull List<Course> courses) {

        // Définition des lignes pour le ListView + l'action du clic sur chaque ligne qui renvoie sur une nouvelle activité
        ListView lv_items = findViewById(R.id.lv_items);
        lv_items.setAdapter(new ArrayAdapter<Course>(getApplicationContext(), R.id.lv_items, courses) {
            /*
            méthode utilisée à la génération de la ListView, renvoie une View qui est affichée dans la liste
             */
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout ll_line = new LinearLayout(getContext());
                ll_line.setPadding(0, 30, 0, 30);
                ll_line.setOrientation(LinearLayout.HORIZONTAL);
                ll_line.setGravity(Gravity.CENTER_VERTICAL);

                // Logo de la matière
                ImageView iv_logo = new ImageView(getContext());
                iv_logo.setImageResource(
                        // à partir des resources
                        getResources().getIdentifier(
                                // à partir du nom du logo
                                this.getItem(position).getM_s_logo(),
                                "drawable",
                                getPackageName()
                        )
                );
                // change la couleur du logo en blanc
                iv_logo.setColorFilter(getResources().getColor(R.color.default_black), PorterDuff.Mode.SRC_IN);
                ll_line.addView(iv_logo);

                Space space = new Space(getContext());
                space.setLayoutParams(new LinearLayout.LayoutParams(25, 1));
                ll_line.addView(space);

                // Le dernier enfant doit forcément être le nom de la matière
                // Nom de la matière
                TextView tv_courseName = new TextView(getContext());
                tv_courseName.setText(this.getItem(position).getM_s_name());
                tv_courseName.setTextSize(25);
                ll_line.addView(tv_courseName);

                // Au clic sur la matière
                ll_line.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // on récupère le nom et le logo de la matière sur laquelle on a cliqué
                        LinearLayout ll_line = (LinearLayout) view;
                        ImageView iv_courseLogo = (ImageView) ((LinearLayout) view).getChildAt(0);
                        TextView tv_courseName = (TextView) ll_line.getChildAt(
                                ll_line.getChildCount()-1
                        );


                        Intent i = new Intent(getContext(), CoursesActivity.class);

                        // on passe le nom de la matière à la nouvelle activité
                        i.putExtra(CoursesActivity.COURSE, tv_courseName.getText());
                        // on passe le nom et prénom de l'utilisateur à la nouvelle activité
                        i.putExtra(HomeActivity.LASTNAME, m_s_lastName);
                        i.putExtra(HomeActivity.FIRSTNAME, m_s_firstName);

                        startActivity(i);
                    }
                });

                return ll_line;
            }
        });
    }

    // afficher la navigationBar en blanc avec les boutons noirs
    public void setNavigationBarColors() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }

    public void setupDAOs() {
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
        courseDAO = db.courseDAO();
    }


    // Classes privées

    private class CoursesAsyncTask extends android.os.AsyncTask<Void, Void, List<Course>> {

        @Override
        protected List<Course> doInBackground(Void... voids) {
            return courseDAO.getAllCourses();
        }

        @Override
        protected void onPostExecute(List<Course> courses) {
            setListView(courses);
        }
    }
}