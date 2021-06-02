package com.example.loustics;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.DAO;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.CheckMCQ;
import com.example.loustics.models.Open;
import com.example.loustics.models.Question;
import com.example.loustics.models.QuestionFrame;
import com.example.loustics.models.RadioMCQ;
import com.example.loustics.models.YesNo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChaptersActivity extends AppCompatActivity {

    private String m_s_courseName;
    private String m_s_chapterName;
    AppDatabase db;
    private Class<? extends QuestionFrame> m_qf_questionType;
    private static int s_i_nombreQuestions = 15;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        getIntentValues();
        setDAOs();
        defineQuestionFrameType();

        // lance la récupération de toutes les questions en lien avec ce chapitre
        new QuestionsAsyncTask().execute();
    }

    public void defineQuestionFrameType() {
        int rand = (int) (Math.random() * (4 - 1));   // 0 -> 3
        // TODO
        rand = 0;

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
        m_s_courseName = getIntent().getStringExtra("COURSE");
        m_s_chapterName = getIntent().getStringExtra("CHAPTER");
    }

    public void setDAOs() {
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    public void setListView(@NotNull List<Question> questions) {
        // Définition des lignes pour le ListView
        ListView lv_items = findViewById(R.id.lv_items);
        lv_items.setAdapter(new ArrayAdapter<Question>(getApplicationContext(), R.id.lv_items, questions) {
            // méthode utilisée à la génération de la ListView, renvoie une View qui est affichée dans la liste
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                try {
                    // Nouvelle question du type choisis aléatoirement
                    QuestionFrame questionFrame = m_qf_questionType.newInstance();
                    questionFrame.setContext(getContext());
                    questionFrame.setQuestion(this.getItem(position));  // assigne une réponse aléatoire

                    View v = questionFrame.getView();
                    // mettre de l'espacement entre les questions
                    v.setPadding(5, 50, 5, 50);
                    return v;

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
        // Récupère toutes les questions de toutes les tables issues de Question qui sont en liées au chapitre et au cours
        protected List<Question> doInBackground(String... strings) {
            List<Question> questions = new ArrayList<>();

            // faire ça pour tous les DAO qui contiennent des Questions, récupère toutes les questions en lien avec ce chapitre + cours
            questions.addAll(db.litteralDAO().getAllQuestions(m_s_chapterName, m_s_courseName));

            return questions;
        }

        @Override
        protected void onPostExecute(List<Question> questions) {
            RandomQuestion.questionsFromAllTypes = questions;
            // lance l'affichage des questions en demandant s_i_nombreQuestions à afficher
            setListView(
                RandomQuestion.getRandomQuestions(s_i_nombreQuestions)
            );
        }
    }

    private static class RandomQuestion {
        public static List<Question> questionsFromAllTypes;

        public static List<Question> getRandomQuestions(int numberOfQuestions) {
            // renvoie le tableau contenant toutes les questions si on demande >= de questions que ce que l'on a
            if (numberOfQuestions >= questionsFromAllTypes.size()) {
                Collections.shuffle(questionsFromAllTypes);
                return questionsFromAllTypes;
            }

            // sinon récupère aléatoirement des questions parmi celles appartenant à ce chapitre
            ArrayList<Question> questions = new ArrayList<>();
            int length = questionsFromAllTypes.size();
            while (questions.size() < numberOfQuestions) {
                int randomValue = (int) (Math.random() * length);
                questions.add(questionsFromAllTypes.get(randomValue));
            }
            return questions;
        }
    }

}
