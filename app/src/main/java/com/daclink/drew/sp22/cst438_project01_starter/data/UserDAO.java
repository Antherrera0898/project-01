package com.daclink.drew.sp22.cst438_project01_starter.data;

import androidx.room.Dao;
import androidx.room.Insert;

import com.daclink.drew.sp22.cst438_project01_starter.model.User;

@Dao
public interface UserDAO {
    @Insert
    void registerUser(User user);
}
