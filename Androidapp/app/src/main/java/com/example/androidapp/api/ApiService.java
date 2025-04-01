package com.example.androidapp.api;

import androidx.room.Delete;

import com.example.androidapp.entities.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
   @GET("categories")
   Call<List<Category>> getCategories();

   @POST("categories")
    Call<Void> addCategory(@Body Category category);

   @Delete("categories/{id}")
    Call<Void> deleteCategory(@Path("id") int id);

}
