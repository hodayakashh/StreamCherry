package com.example.androidapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.R;
import com.example.androidapp.entities.Category;
import com.example.androidapp.viewmodels.CategoryViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCategoryActivity extends AppCompatActivity {

    private CategoryViewModel categoryViewModel;
    private EditText etCategoryName;
    private CheckBox cbPromoted;
    private Button btnSaveCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        etCategoryName = findViewById(R.id.etCategoryName);
        cbPromoted = findViewById(R.id.spinnerPromoted);
        btnSaveCategory = findViewById(R.id.btnAddCategory);


        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        btnSaveCategory.setOnClickListener(v -> {
            String categoryName = etCategoryName.getText().toString().trim();
            boolean isPromoted = cbPromoted.isChecked();

            if(categoryName.isEmpty()){
                Toast.makeText(this, "Please enter a category name", Toast.LENGTH_SHORT).show();
                return;
            }

            Category category = new Category(categoryName, isPromoted);

            categoryViewModel.addCategory(category, new Callback<Category>() {
                @Override
                public void onResponse(Call<Category> call, Response<Category> response) {
                    if(response.isSuccessful() && response.body() != null){
                        runOnUiThread(() -> {
                            Toast.makeText(AddCategoryActivity.this, "Category added successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        });
                    } else {
                        runOnUiThread(() -> {
                            Toast.makeText(AddCategoryActivity.this, "Failed to add category: " + response.code(), Toast.LENGTH_SHORT).show();
                        });
                    }
                }

                @Override
                public void onFailure(Call<Category> call, Throwable t) {
                    runOnUiThread(() -> {
                        Toast.makeText(AddCategoryActivity.this, "Error adding category: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            });
        });
    }
}
