package com.example.androidapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.androidapp.db.AppDB;
import com.example.androidapp.db.CategoryDao;
import com.example.androidapp.entities.Category;

public class EditCategoryActivity extends AppCompatActivity {

    private AppDB db;
    private CategoryDao categoryDao;
    private EditText etItem;
    CheckBox cbIsActive;

    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDB.class, "CategoryDB")
                .allowMainThreadQueries()
                .build();
        categoryDao = db.categoryDao();

        etItem = findViewById(R.id.etCategoryName);
        cbIsActive = findViewById(R.id.spinnerPromoted);

        String categoryName = getIntent().getStringExtra("category");
        boolean isPromoted = getIntent().getBooleanExtra("isPromoted", false);
        etItem.setText(categoryName);
        cbIsActive.setChecked(isPromoted);
        category = categoryDao.getCategoryByName(categoryName);


        Button btnSaveCategory = findViewById(R.id.btnEditCategorySave);
        btnSaveCategory.setOnClickListener(v -> {
            category.setName(etItem.getText().toString());
            category.setPromoted(cbIsActive.isChecked());
            categoryDao.update(category);
            finish();
        });



    }
}
