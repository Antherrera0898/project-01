package com.daclink.drew.sp22.cst438_project01_starter.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.daclink.drew.sp22.cst438_project01_starter.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String dbName = "user";
    private static UserDatabase userDatabase;

    public static synchronized UserDatabase getUserDatabase(Context context){
        if(userDatabase == null){
            userDatabase = Room.databaseBuilder(context, UserDatabase.class, dbName).fallbackToDestructiveMigration().build();
        }

        return userDatabase;
    }

    public abstract UserDAO userDAO();
}
