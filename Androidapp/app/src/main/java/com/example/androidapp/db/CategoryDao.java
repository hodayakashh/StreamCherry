package com.example.androidapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidapp.entities.Category;

import java.util.List;

@Dao
public interface CategoryDao {


    @Query("SELECT * FROM category")
    List<Category> getAllCategories();
    @Query("SELECT * FROM category WHERE name = :name")
    Category getCategoryByName(String name);
    @Insert
    void insert(Category... categories);

    @Update
    void update(Category... categories);

    @Delete
    void delete(Category... categories);
}
