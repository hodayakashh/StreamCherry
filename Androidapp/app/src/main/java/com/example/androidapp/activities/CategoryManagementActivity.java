
package com.example.androidapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;
import com.example.androidapp.adapters.CategoriesListAdapter;
import com.example.androidapp.db.CategoryDao;
import com.example.androidapp.viewmodels.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryManagementActivity extends AppCompatActivity {

    private CategoriesListAdapter adapter;
    private CategoryViewModel categoryViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);


        RecyclerView lstCategories = findViewById(R.id.lstCategories);
        adapter = new CategoriesListAdapter(this, category -> {
            categoryViewModel.deleteCategory(category, new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){
                        Log.d("CategoryManagement", "Category deleted successfully");
                        adapter.notifyDataSetChanged();
                    } else if (response.code() == 404) {
                        Log.e("CategoryManagement", "Category not found");
                    } else {

                        Log.e("CategoryManagement", "Failed to delete category, code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("CategoryManagement", "Error deleting category", t);
                }
            });
        }, category -> {
            Intent intent = new Intent(CategoryManagementActivity.this, EditCategoryActivity.class);
//            intent.putExtra("id",category.getId());
            intent.putExtra("category", category.getName());
            intent.putExtra("isPromoted", category.isPromoted());
            startActivity(intent);
        });


        lstCategories.setAdapter(adapter);
        lstCategories.setLayoutManager(new LinearLayoutManager(this));
        categoryViewModel.getCategories().observe(this, categories ->
                adapter.setCategories(categories));

        FloatingActionButton btnAddCategory = findViewById(R.id.btnAddCategory);
        btnAddCategory.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryManagementActivity.this, AddCategoryActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}