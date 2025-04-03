package com.example.androidapp.repositories;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.androidapp.api.CategoryApi;
import com.example.androidapp.db.AppDB;
import com.example.androidapp.db.CategoryDao;
import com.example.androidapp.entities.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {

    private CategoryDao categoryDao;
    private CategoryListData categoryListData;
    private CategoryApi categoryApi;

    public CategoryRepository(Context context) {
        AppDB db = AppDB.getInstance(context);
        categoryDao = db.categoryDao();
        categoryListData = new CategoryListData();
        categoryApi = new CategoryApi();
    }

    class CategoryListData extends MutableLiveData<List<Category>> {
        public CategoryListData(){
            super();
            new Thread(() -> {
                List<Category> categories = categoryDao.getAllCategories();
                postValue(categories);
            }).start();
        }


        @Override
        protected void onActive() {
            super.onActive();

            // Load initial data from local database
            loadFromLocalDatabase();

            // Fetch fresh data from API and sync
            fetchAndSyncWithApi();
        }

        private void loadFromLocalDatabase() {
            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    List<Category> localCategories = categoryDao.getAllCategories();
                    postValue(localCategories);
                } catch (Exception e) {
                    Log.e("CategoryRepo", "error in loading categories from local database", e);
                }
            });
        }

        private void fetchAndSyncWithApi() {
            // 1. Log that we're starting the API call
            Log.d("API", "Starting to fetch categories from server");

            // 2. Make the API call
            categoryApi.getCategories(new Callback<List<Category>>() {

                // 3. This runs when we get a response from server
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                    // 4. Check if response was successful (HTTP 200-299)
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("API", "Got successful response with categories");

                        // 5. Process the data we received
                        processApiResponse(response.body());
                    } else {
                        // 6. Handle unsuccessful responses (404, 500, etc.)
                        Log.w("API", "Server response problem: " + response.body());
                    }
                }

                // 7. This runs if the request completely fails (no internet, etc.)
                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                    Log.e("API", "Failed to call API", t);
                }
            });
        }

        private void processApiResponse(List<Category> serverCategories) {
            // 8. Run this in background thread to avoid blocking UI
            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    // 9. Get current categories from local database
                    List<Category> localCategories = categoryDao.getAllCategories();

                    // 10. Create a map for quick lookup by name
                    Map<String, Category> localMap = new HashMap<>();
                    for (Category cat : localCategories) {
                        localMap.put(cat.getName(), cat);
                    }

                    // 11. Compare server data with local data
                    for (Category serverCat : serverCategories) {
                        Category localCat = localMap.get(serverCat.getName());

                        if (localCat == null) {
                            // 12. New category - save to database
                            categoryDao.insert(serverCat);
                        } else {
                            // 13. Existing category - update if needed
                            serverCat.setId(localCat.getId()); // Keep same database ID
                            categoryDao.update(serverCat);
                        }
                    }

                    // 14. Get fresh list from database and notify UI
                    postValue(categoryDao.getAllCategories());

                } catch (Exception e) {
                    Log.e("DB", "Error saving categories", e);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();
        }
    }

    // Ensure these methods are inside the CategoryRepository class
    public MutableLiveData<List<Category>> getAllCategories() {
        return categoryListData;
    }

    public void deleteCategoryFromServer(Category category, Callback<Void> callback) {
        // נניח שהקטגוריה כוללת שדה serverId, שהוא המזהה שמגיע מהשרת (MongoDB)
        String serverId = category.getServerId();
        new Thread(() -> {
            categoryDao.delete(category);
            List<Category> categories = categoryDao.getAllCategories();
            categoryListData.postValue(categories);
        }).start();


        // קריאה למחיקת הקטגוריה מהשרת
        categoryApi.deleteCategory(serverId, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Log.d("CategoryRepo", "Category deleted successfully from server");
                    // מחיקה ממסד הנתונים המקומי
                    new Thread(() -> {
                        categoryDao.delete(category);
                        List<Category> categories = categoryDao.getAllCategories();
                        categoryListData.postValue(categories);
                    }).start();
                } else {
                    Log.e("CategoryRepo", "Failed to delete category, code: " + response.code());
                }
                callback.onResponse(call, response);
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("CategoryRepo", "Error deleting category from server", t);
                callback.onFailure(call, t);
            }
        });
    }

    public void addCategoryToServer(Category category, Callback<Category> callback) {
        // קריאה לשרת להוספת קטגוריה
        categoryApi.addCategory(category, new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Category addedCategory = response.body();
                    // עדכון מסד הנתונים המקומי עם הקטגוריה שהתקבלה מהשרת
                    new Thread(() -> {
                        categoryDao.insert(addedCategory);
                        List<Category> updatedCategories = categoryDao.getAllCategories();
                        categoryListData.postValue(updatedCategories);
                    }).start();
                }
                callback.onResponse(call, response);
            }
            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }


    public void updateCategoryOnServer(Category category, Callback<Category> callback) {

        categoryApi.updateCategory(category,new Callback<Category>() {
            @Override
            public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Category updatedCategory = response.body();
                    updatedCategory.setId(category.getId()); // Ensure we keep the same ID

                    new Thread(() -> {
                        categoryDao.update(updatedCategory);
                        List<Category> updatedCategories = categoryDao.getAllCategories();
                        categoryListData.postValue(updatedCategories);
                    }).start();
                } else {
                    Log.e("CategoryRepo", "Failed to update category, code: " + response.code());
                }
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                Log.e("CategoryRepo", "Error updating category on server", t);
                callback.onFailure(call, t);
            }
        });
    }



    public LiveData<Category> getCategoryByName(String name) {
        return categoryDao.getCategoryByName(name);
    }

    public void updateCategorySelection(int categoryId, boolean isSelected) {
        new Thread(() -> {
            categoryDao.updateSelection(categoryId, isSelected);
        }).start();
    }

    public LiveData<List<Category>> getSelectedCategories() {
        return categoryDao.getSelectedCategories();
    }
}
