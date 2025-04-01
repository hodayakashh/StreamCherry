package com.example.androidapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidapp.db.AppDB;
import com.example.androidapp.db.CategoryDao;
import com.example.androidapp.entities.Category;

public class AddCategoryActivity extends AppCompatActivity {

    private AppDB db;
    private CategoryDao categoryDao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        // Initialize views and set up listeners here
        db = AppDB.getInstance(getApplicationContext());
        categoryDao = db.categoryDao();
        // Initialize the database and DAO
        Button btnSaveCategory = findViewById(R.id.btnAddCategory);
        btnSaveCategory.setOnClickListener(v -> {
            EditText etItem = findViewById(R.id.etCategoryName);
            CheckBox cbIsActive = findViewById(R.id.spinnerPromoted);
            Category category = new Category(etItem.getText().toString(), cbIsActive.isChecked());
            categoryDao.insert(category);
            finish();
        });
    }

}
