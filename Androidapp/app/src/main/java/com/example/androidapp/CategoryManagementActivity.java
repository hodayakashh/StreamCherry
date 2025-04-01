
package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.androidapp.adapters.CategoriesListAdapter;
import com.example.androidapp.db.AppDB;
import com.example.androidapp.db.CategoryDao;
import com.example.androidapp.entities.Category;
import com.example.androidapp.viewmodels.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CategoryManagementActivity extends AppCompatActivity {

    private AppDB db;
    private CategoryDao categoryDao;
    private CategoriesListAdapter adapter;
    private List<Category> categories;
    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        categoryViewModel.getCategories().observe(this, categories -> {
            adapter.setCategories(categories);
        });

        RecyclerView lstCategories = findViewById(R.id.lstCategories);
        adapter = new CategoriesListAdapter(this, category -> {
            categoryDao.delete(category);
            categories = categoryDao.getAllCategories();
            adapter.setCategories(categories);
        }, category -> {
            Intent intent = new Intent(CategoryManagementActivity.this, EditCategoryActivity.class);
//            intent.putExtra("id",category.getId());
            intent.putExtra("category", category.getName());
            intent.putExtra("isPromoted", category.isPromoted());
            startActivity(intent);
        });
        lstCategories.setAdapter(adapter);
        lstCategories.setLayoutManager(new LinearLayoutManager(this));
        categories = new ArrayList<>();

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDB.class, "CategoryDB")
                .allowMainThreadQueries()
                .build();
        // Initialize the database and DAO
        categoryDao = db.categoryDao();

        categories = categoryDao.getAllCategories();
        adapter.setCategories(categories);
        FloatingActionButton btnAddCategory = findViewById(R.id.btnAddCategory);
        btnAddCategory.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryManagementActivity.this, AddCategoryActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        categories = categoryDao.getAllCategories();
        adapter.setCategories(categories);
    }
}