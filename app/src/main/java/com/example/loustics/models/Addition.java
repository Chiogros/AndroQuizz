package com.example.loustics.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Addition",
foreignKeys = {
    @ForeignKey(entity = Chapter.class, parentColumns = { "name", "courseName" }, childColumns = { "chapterName", "courseName" })
},
primaryKeys = {
    "chapterName", "courseName", "rangeMinOperand1", "rangeMaxOperand1", "rangeMinOperand2", "rangeMaxOperand2"
},
indices = {
    @Index("chapterName"),
    @Index("courseName"),
    @Index("rangeMinOperand1"),
    @Index("rangeMaxOperand1"),
    @Index("rangeMinOperand2"),
    @Index("rangeMaxOperand2")
})
public class Addition extends Calculation {

    @NonNull
    @ColumnInfo(name = "chapterName")
    protected final String m_s_chapterName;

    @NonNull
    @ColumnInfo (name = "courseName")
    protected final String m_s_courseName;

    @ColumnInfo (name = "rangeMinOperand1")
    protected final int m_i_rangeMinOperand1;

    @ColumnInfo (name = "rangeMaxOperand1")
    protected final int m_i_rangeMaxOperand1;

    @ColumnInfo (name = "rangeMinOperand2")
    protected final int m_i_rangeMinOperand2;

    @ColumnInfo (name = "rangeMaxOperand2")
    protected final int m_i_rangeMaxOperand2;


    public Addition(@NotNull String m_s_chapterName, @NotNull String m_s_courseName, int m_i_rangeMinOperand1, int m_i_rangeMaxOperand1, int m_i_rangeMinOperand2, int m_i_rangeMaxOperand2) {
        super(m_i_rangeMinOperand1, m_i_rangeMaxOperand1, m_i_rangeMinOperand2, m_i_rangeMaxOperand2);
        this.m_s_chapterName = m_s_chapterName;
        this.m_s_courseName = m_s_courseName;
        this.m_i_rangeMinOperand1 = m_i_rangeMinOperand1;
        this.m_i_rangeMaxOperand1 = m_i_rangeMaxOperand1;
        this.m_i_rangeMinOperand2 = m_i_rangeMinOperand2;
        this.m_i_rangeMaxOperand2 = m_i_rangeMaxOperand2;
    }

    public int getM_i_rangeMinOperand1() {
        return m_i_rangeMinOperand1;
    }

    public int getM_i_rangeMaxOperand1() {
        return m_i_rangeMaxOperand1;
    }

    public int getM_i_rangeMinOperand2() {
        return m_i_rangeMinOperand2;
    }

    public int getM_i_rangeMaxOperand2() {
        return m_i_rangeMaxOperand2;
    }

    @NotNull
    public String getM_s_chapterName() {
        return m_s_chapterName;
    }

    @NotNull
    public String getM_s_courseName() {
        return m_s_courseName;
    }

    public String getM_s_subject() {
        return m_d_operand1 + " + " + m_d_operand2 + " =";
    }

    public int getResult() {
        return m_d_operand1 + m_d_operand2;
    }
}