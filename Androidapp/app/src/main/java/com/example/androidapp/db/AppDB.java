package com.example.androidapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidapp.entities.Category;

@Database(entities ={Category.class}, version = 3, exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    private static AppDB instance;

    public abstract CategoryDao categoryDao();

    public static synchronized AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDB.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
