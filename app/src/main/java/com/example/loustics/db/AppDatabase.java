package com.example.loustics.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.loustics.models.Achievement;
import com.example.loustics.models.Addition;
import com.example.loustics.models.Chapter;
import com.example.loustics.models.Course;
import com.example.loustics.models.Image;
import com.example.loustics.models.Litteral;
import com.example.loustics.models.Multiplication;
import com.example.loustics.models.Soustraction;
import com.example.loustics.models.User;

@Database(entities = {Course.class, Chapter.class, Litteral.class, Image.class, Addition.class, Soustraction.class, Multiplication.class, User.class, Achievement.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract CourseDAO courseDAO();
    public abstract ChapterDAO chapterDAO();
    public abstract LitteralDAO litteralDAO();
    public abstract ImageDAO imageDAO();
    public abstract CalculationDAO calculationDAO();
    public abstract UserDAO userDAO();
    public abstract AchievementDAO achievementDAO();

}