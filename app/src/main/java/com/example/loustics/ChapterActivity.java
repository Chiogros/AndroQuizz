package com.example.loustics;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import androidx.appcompat.widget.Toolbar;

import com.example.loustics.models.Chapter;
import com.example.loustics.models.Course;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ChapterActivity extends AppCompatActivity {

    public static final String COURSE = "";
    private String m_sCourseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        this.m_sCourseName = getIntent().getStringExtra(COURSE);

        TextView t = (TextView) findViewById(R.id.tv_course);
        t.setText(this.m_sCourseName);

        // setListView();
    }

    public void setListView() {
        // n'affiche rien si la liste est vide
        if (Course.getCourses().size() == 0) {
            return ;
        }

        // Définition des lignes pour le ListView + l'action du click sur chaque ligne qui renvoit sur une nouvelle activité
        ListView lv_items = findViewById(R.id.lv_items);
        lv_items.setAdapter(new ArrayAdapter<Course>(getApplicationContext(), R.id.lv_items, Course.getCourses()) {
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
                iv_logo.setImageResource(this.getItem(position).getLogo());
                ll_line.addView(iv_logo);

                Space space = new Space(getContext());
                space.setLayoutParams(new LinearLayout.LayoutParams(25, 1));
                ll_line.addView(space);

                // Le dernier enfant doit forcément être le nom de la matière
                // Nom de la matière
                TextView tv_logo = new TextView(getContext());
                tv_logo.setText(this.getItem(position).getName());
                tv_logo.setTextSize(25);
                ll_line.addView(tv_logo);

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

                        // on passe le nom de la matière à la nouvelle activité
                        Intent i = new Intent(getContext(), ChapterActivity.class);
                        i.putExtra(ChapterActivity.COURSE, tv_courseName.getText());

                        // Si la fonctionnalité est supportée par la version d'Android
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                            startActivity(i);
                        } else {
                            iv_courseLogo.setTransitionName("iv_courseLogo");
                            tv_courseName.setTransitionName("tv_courseName");
                            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(ChapterActivity.this).toBundle());
                        }
                    }
                });

                return ll_line;
            }
        });
    }

}
