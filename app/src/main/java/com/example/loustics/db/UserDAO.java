package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.loustics.models.Litteral;
import com.example.loustics.models.User;

import java.util.List;
@Dao
public interface UserDAO {

    @Query("SELECT * FROM User WHERE firstName = :firstName AND lastName = :lastName")
    List<User> getUser(String firstName, String lastName);

    @Query("SELECT * FROM User ORDER BY firstName")
    List<User> getAllUsers();

    @Query("SELECT photo FROM User WHERE firstName = :firstName AND lastName = :lastName LIMIT 1")
    String getPhoto(String firstName, String lastName);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
