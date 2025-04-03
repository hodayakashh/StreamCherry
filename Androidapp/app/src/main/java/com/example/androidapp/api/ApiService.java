package com.example.androidapp.api;

import com.example.androidapp.entities.Category;
import com.example.androidapp.entities.Movie;
import com.example.androidapp.entities.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
    @GET("categories")
    Call<List<Category>> getCategories(@Header("userid") String userId);

    @POST("categories")
    Call<Category> addCategory(@Body Category category);

    @DELETE("categories/{id}")
    Call<Void> deleteCategory(@Path("id") String serverId);

    @PATCH("categories/{id}")
    Call<Category> updateCategory(@Path("id") String serverId, @Body Category category);

}