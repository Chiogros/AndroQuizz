package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.loustics.models.Litteral;
import com.example.loustics.models.User;

import java.util.List;
@Dao
public interface UserDAO {

    @Query("SELECT * FROM User WHERE lastName = :lastName AND firstName = :firstName")
    List<User> getUser(String lastName, String firstName);

    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    @Insert
    void insert(User user);
}
