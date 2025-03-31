package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.androidapp.adapters.CategoriesListAdapter;
import com.example.androidapp.db.AppDB;
import com.example.androidapp.db.CategoryDao;
import com.example.androidapp.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryManagementActivity extends AppCompatActivity {

    private AppDB db;
    private CategoryDao categoryDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);


        RecyclerView lstCategories = findViewById(R.id.lstCategories);
        final CategoriesListAdapter adapter = new CategoriesListAdapter(this);
        lstCategories.setAdapter(adapter);
        lstCategories.setLayoutManager(new LinearLayoutManager(this));
        List<Category> categories = new ArrayList<>();
         db = Room.databaseBuilder(getApplicationContext(),
                AppDB.class, "CategoryDB")
                .allowMainThreadQueries()
                .build();
        CategoryDao categoryDao = db.categoryDao();

        Button btnAddCategory = findViewById(R.id.btnAddCategory);
        btnAddCategory.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryManagementActivity.this, AddCategoryActivity.class);
        });

    }

}