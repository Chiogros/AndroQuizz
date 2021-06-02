package com.example.loustics.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.loustics.models.Chapter;
import com.example.loustics.models.Course;
import com.example.loustics.models.Litteral;
import com.example.loustics.models.Question;
import com.example.loustics.models.User;

@Database(entities = {Course.class, Chapter.class, Litteral.class, User.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract CourseDAO courseDAO();
    public abstract ChapterDAO chapterDAO();
    public abstract LitteralDAO litteralDAO();
    public abstract UserDAO userDAO();

}