package com.example.loustics.db;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Insert;

import com.example.loustics.models.MCQ;
import com.example.loustics.models.RadioMCQ;

import java.util.List;


@Dao
public interface RadioMCQ_DAO {
    @Query("SELECT * FROM RadioMCQ")
    List<RadioMCQ> getAllMCQ();

    @Insert
    void insert(RadioMCQ radiomcq);

    @Delete
    void delete(RadioMCQ radiomcq);

    @Update
    void update(RadioMCQ radiomcq);


}
