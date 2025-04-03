package com.example.androidapp.api;

import com.example.androidapp.entities.Category;
import com.example.androidapp.response.MovieCategoryResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MovieApiService {
    @GET("api/movies")
    Call<MovieCategoryResponse> getAllMovies(@Header("userid") String userId);

    @GET("api/categories")
    Call<List<Category>> getAllCategories();
    @Multipart
    @POST("api/movies")
    Call<ResponseBody> addMovie(
            @Part("title") RequestBody title,
            @Part("description") RequestBody description,
            @Part("releaseYear") RequestBody releaseYear,
            @Part("duration") RequestBody duration,
            @Part("cast") RequestBody cast,
            @Part List<MultipartBody.Part> categories,
            @Part MultipartBody.Part movieFile,
            @Part MultipartBody.Part movieImage
    );

}