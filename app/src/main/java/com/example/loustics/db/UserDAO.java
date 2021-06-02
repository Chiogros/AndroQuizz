package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.loustics.models.User;

import java.util.List;
@Dao
public interface UserDAO {

    @Query
            ("SELECT * FROM user") List<User> getAllUsers();

    @Insert
            void insert(User NewUser);
}
