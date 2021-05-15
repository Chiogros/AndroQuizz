package com.example.loustics;

import android.content.Intent;
import android.database.DataSetObserver;
import android.media.Image;
import android.os.Bundle;

import com.example.loustics.models.Course;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("Matières");

        Course c = new Course("Maths");
        c = new Course("Français");

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
                        startActivity(i);
                    }
                });

                return ll_line;
            }
        });
    }
}