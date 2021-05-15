package com.example.loustics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.m_sCourseName = getIntent().getStringExtra(COURSE);

        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(this.m_sCourseName);

        Chapter c = new Chapter();
        c = new Chapter();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (Course.getCourses().size() == 0) {
            return ;
        }
        ListView lv_items = findViewById(R.id.lv_items);
        lv_items.setAdapter(new ArrayAdapter<Course>(getApplicationContext(), R.id.lv_items, Course.getCourses()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout ll_line = new LinearLayout(getContext());

                /*ImageView iv_logo = new ImageView(getContext());
                iv_logo.setImageDrawable(this.getItem(position).getLogo());
                ll_line.addView(iv_logo);*/

                // Le dernier enfant doit forcément être le nom de la matière
                TextView tv_logo = new TextView(getContext());
                tv_logo.setText(this.getItem(position).getName());
                ll_line.addView(tv_logo);

                ll_line.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LinearLayout ll_line = (LinearLayout) view;
                        TextView tv_courseName = (TextView) ll_line.getChildAt(
                                ll_line.getChildCount()-1
                        );

                        Intent i = new Intent(getContext(), ChapterActivity.class);
                        i.putExtra(ChapterActivity.COURSE, tv_courseName.getText());
                    }
                });

                return ll_line;
            }
        });
    }

}
