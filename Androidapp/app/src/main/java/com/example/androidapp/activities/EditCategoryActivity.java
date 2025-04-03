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

public class EditCategoryActivity extends AppCompatActivity {

    private CategoryViewModel categoryViewModel;
    private EditText etItem;
    private CheckBox cbIsActive;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        // Initialize ViewModel
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        // Initialize UI components
        etItem = findViewById(R.id.etCategoryName);
        cbIsActive = findViewById(R.id.spinnerPromoted);

        // Get data from intent
        String categoryName = getIntent().getStringExtra("category");
        boolean isPromoted = getIntent().getBooleanExtra("isPromoted", false);
        etItem.setText(categoryName);
        cbIsActive.setChecked(isPromoted);

        // Get the category from ViewModel
        categoryViewModel.getCategoryByName(categoryName).observe(this, category -> {
            if (category != null) {
                this.category = category;
                // Update UI with current values (in case they're different from intent)
                etItem.setText(category.getName());
                cbIsActive.setChecked(category.isPromoted());
            }
        });

        // Set up save button
        Button btnSaveCategory = findViewById(R.id.btnEditCategorySave);
        btnSaveCategory.setOnClickListener(v -> {
            if (category != null) {
                // Update the category object
                category.setName(etItem.getText().toString());
                category.setPromoted(cbIsActive.isChecked());

                // Use ViewModel to update
                categoryViewModel.updateCategory(category, new Callback<Category>() {
                    @Override
                    public void onResponse(Call<Category> call, Response<Category> response) {
                        if (response.isSuccessful()) {
                            // Update was successful
                            Toast.makeText(EditCategoryActivity.this, "Category updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // Handle error
                            Toast.makeText(EditCategoryActivity.this, "Failed to update category: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Category> call, Throwable t) {
                        // Handle failure
                        Toast.makeText(EditCategoryActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                // Show confirmation and finish
                Toast.makeText(this, "Category updated", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error: Category not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}