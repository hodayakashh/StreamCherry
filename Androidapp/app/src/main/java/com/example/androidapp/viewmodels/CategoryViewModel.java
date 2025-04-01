package com.example.androidapp.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.entities.Category;
import com.example.androidapp.repositories.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends ViewModel {

    private CategoryRepository categoryRepository;
    private MutableLiveData<List<Category>> categories;

    public CategoryViewModel(Context context) {
        categoryRepository = new CategoryRepository(context);
        categories = new MutableLiveData<>();
        categories.setValue(categoryRepository.getAllCategories().getValue());
    }

    // Expose the data as immutable LiveData
    public LiveData<List<Category>> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        categoryRepository.addCategory(category);
    }

    public void deleteCategory(Category category) {
        categoryRepository.deleteCategory(category);
    }

    public void updateCategory(Category category) {
        categoryRepository.updateCategory(category);
    }
}
