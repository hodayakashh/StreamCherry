package com.example.androidapp.repositories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.androidapp.db.AppDB;
import com.example.androidapp.db.CategoryDao;
import com.example.androidapp.entities.Category;

import java.util.List;

public class CategoryRepository {

    private CategoryDao categoryDao;
    private CategoryListData categoryListData;

    public CategoryRepository (Context context) {
        AppDB db = AppDB.getInstance(context);
        categoryDao = db.categoryDao();
        categoryListData = new CategoryListData();

    }

    class CategoryListData extends MutableLiveData<List<Category>> {

        public CategoryListData(){
            super();
            setValue(categoryDao.getAllCategories());
        }
        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() -> {
                // Fetch data from the database
                List<Category> categories = categoryDao.getAllCategories();
                postValue(categories);
            }).start();
        }

        @Override
        protected void onInactive() {
            super.onInactive();
            // Cleanup if needed
        }
    }
    public MutableLiveData<List<Category>> getAllCategories() {
        return categoryListData;
    }

    public void addCategory(Category category) {
    }

    public void deleteCategory(Category category) {
    }

    public void updateCategory(Category category) {
    }
}
