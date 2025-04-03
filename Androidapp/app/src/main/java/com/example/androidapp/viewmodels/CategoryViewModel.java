package com.example.androidapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.MyApplication;
import com.example.androidapp.entities.Category;
import com.example.androidapp.repositories.CategoryRepository;

import org.jetbrains.annotations.Async;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Callback;

public class CategoryViewModel extends ViewModel {

    private CategoryRepository categoryRepository;
    private MutableLiveData<List<Category>> categories;

    MyApplication app = MyApplication.getInstance();

    public CategoryViewModel() {
        categoryRepository = new CategoryRepository(app.getAppContext());
        categories = new MutableLiveData<>();
        categories.setValue(categoryRepository.getAllCategories().getValue());
    }

    // Expose the data as immutable LiveData
    public LiveData<List<Category>> getCategories() {
        return categoryRepository.getAllCategories();
    }

    public LiveData<Category> getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    public void deleteCategory(Category category, Callback<Void> callback) {
        categoryRepository.deleteCategoryFromServer(category, callback);
    }


    public void addCategory(Category category, Callback<Category> callback) {
        categoryRepository.addCategoryToServer(category, callback);
    }

    public void updateCategory(Category category, Callback<Category> callback) {
        categoryRepository.updateCategoryOnServer(category, callback);
    }
    public void updateCategorySelection(int categoryId, boolean isSelected) {
        categoryRepository.updateCategorySelection(categoryId, isSelected);
    }

    public LiveData<List<Category>> getSelectedCategories() {
        return categoryRepository.getSelectedCategories();
    }

}
