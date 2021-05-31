package com.example.loustics;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.DAO;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.Chapter;
import com.example.loustics.models.CheckMCQ;
import com.example.loustics.models.Open;
import com.example.loustics.models.Question;
import com.example.loustics.models.QuestionFrame;
import com.example.loustics.models.RadioMCQ;
import com.example.loustics.models.YesNo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChaptersActivity extends AppCompatActivity {

    public static final String COURSE = "";
    public static final String CHAPTER = "";
    private String m_s_courseName;
    private String m_s_chapterName;
    AppDatabase db;
    private Class<? extends QuestionFrame> m_qf_questionType;
    private DAO m_DAOinterface;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        getIntentValues();
        setDAOs();
        defineQuestionFrameType();
    }

    public void defineQuestionFrameType() {
        int rand = (int) (Math.random() * (4 - 1));   // 0 -> 3

        switch (rand) {
            case 0:
                m_qf_questionType = YesNo.class;
                break;
            case 1:
                m_qf_questionType = Open.class;
                break;
            case 2:
                m_qf_questionType = CheckMCQ.class;
                break;
            case 3:
                m_qf_questionType = RadioMCQ.class;
                break;
            default:
                break;
        }
    }

    public void getIntentValues() {
        m_s_courseName = getIntent().getStringExtra(COURSE);
        m_s_chapterName = getIntent().getStringExtra(CHAPTER);
    }

    public void setDAOs() {
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    public void setListView(@NotNull List<Question> questions) {
        // Définition des lignes pour le ListView
        ListView lv_items = findViewById(R.id.lv_items);
        lv_items.setAdapter(new ArrayAdapter<Chapter>(getApplicationContext(), R.id.lv_items, questions) {
            // méthode utilisée à la génération de la ListView, renvoie une View qui est affichée dans la liste
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                QuestionFrame questionFrame;
                try {
                    questionFrame = m_qf_questionType.newInstance();
                    questionFrame.setContext(getContext());
                    questionFrame.setQuestion(new RandomQuestion().getQuestion());
                    return questionFrame.getView();
                // les catch sont obligatoires pour le newInstance()
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

                // Échec du newInstance()
                return new View(getContext());
            }
        });
    }


    // Classes privées

    private class QuestionsAsyncTask extends android.os.AsyncTask<String, Void, List<Question>> {
        @Override
        protected List<Question> doInBackground(String... strings) {
            return m_qf_questionType;
        }

        @Override
        protected void onPostExecute(List<Question> questions) {
            setListView(questions);
        }
    }

    private class RandomQuestion {
        public List<Question> questionsFromAllTypes;
        RandomQuestion(String chapterName, String courseName) {
            questionsFromAllTypes = new ArrayList<>();

        }
    }

}
