package com.daclink.drew.sp22.cst438_project01_starter.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.daclink.drew.sp22.cst438_project01_starter.model.User;

@Dao
public interface UserDAO {
    @Insert
    void registerUser(User user);

    @Query("SELECT * from users where username=(:username) and password=(:password)")
    User login(String username, String password);
}
