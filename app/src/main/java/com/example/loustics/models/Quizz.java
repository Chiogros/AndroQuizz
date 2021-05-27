package com.example.loustics.models;

import android.content.Context;
import android.widget.ArrayAdapter;

public class Quizz {

    private ArrayAdapter<QuestionFrame> m_aa_questionFrames;
    private Context m_c_context;
    private int m_i_resource;

    public Quizz(Context context, int resource) {
        this.m_aa_questionFrames = new ArrayAdapter<QuestionFrame>(context, resource);
        this.m_c_context = context;
        this.m_i_resource = resource;
    }

    public void addQuestionFrame(QuestionFrame questionFrame) throws Exception {
        if (questionFrame.getContext() != this.m_c_context)
            throw new Exception("This question frame is not in the same context that this quizz.");

        this.m_aa_questionFrames.add(questionFrame);
    }

    public void display() {
        // TODO
    }

    public Context getContext() {
        return this.m_c_context;
    }

    public QuestionFrame getQuestionFrame(int position) {
        return this.m_aa_questionFrames.getItem(position);
    }

    public void insertQuestion(QuestionFrame questionFrame, int position) throws Exception {
        try {
            this.m_aa_questionFrames.insert(questionFrame, position);
        } catch (IndexOutOfBoundsException ex) {
            throw new Exception("Cannot add this question frame to the " + position + " position while the array is " + this.m_aa_questionFrames.getCount() + " elements long.");
        }
    }

    public void removeQuestion(QuestionFrame questionFrame) {
        this.m_aa_questionFrames.remove(questionFrame);
    }
}
