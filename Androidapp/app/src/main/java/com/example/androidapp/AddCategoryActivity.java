package com.example.androidapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.androidapp.db.AppDB;
import com.example.androidapp.db.CategoryDao;

public class AddCategoryActivity extends AppCompatActivity {

    private AppDB db;
    private CategoryDao categoryDao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        // Initialize views and set up listeners here
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDB.class, "CategoryDB")
                .allowMainThreadQueries()
                .build();
        CategoryDao categoryDao = db.categoryDao();

        Button btnSaveCategory = findViewById(R.id.btnAddCategory);
        btnSaveCategory.setOnClickListener(v -> {
            // Handle the save category action here
            // For example, you can save the category to a database or update the UI
        });
    }

}
