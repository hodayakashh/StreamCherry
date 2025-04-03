package com.example.androidapp.repositories;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.androidapp.ApiClient;
import com.example.androidapp.AppDatabase;
import com.example.androidapp.db.MovieDao;
import com.example.androidapp.entities.Category;
import com.example.androidapp.api.MovieApiService;
import com.example.androidapp.entities.Movie;
import com.example.androidapp.response.MovieCategoryResponse;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private final MovieDao movieDao;
    private final MovieApiService apiService;
    private final LiveData<List<Movie>> allMovies;
    private final Application application;

    public MovieRepository(Application application) {
        this.application = application;
        AppDatabase db = AppDatabase.getInstance(application);
        movieDao = db.movieDao();
        allMovies = movieDao.getAllMovies();

        // Get JWT token from SharedPreferences
        SharedPreferences prefs = application.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String token = prefs.getString("authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2N2U3YzQ4ZmE0ZTIxNjZiNjQyNTdlOGUiLCJ1c2VyX25hbWUiOiJzaGFoYWsiLCJtYWlsIjoic2hhaGFrMzRAZ21haWwuY29tIiwicGhvbmUiOiIwNTI4MzAxMDAzIiwicHJvZmlsZVBpY3R1cmUiOiIvaW1hZ2VzL2F2YXRhcnMvYXZhdGFyMS53ZWJwIiwibWFuYWdlciI6dHJ1ZSwiaWF0IjoxNzQzNjA3ODU5LCJleHAiOjE3NDM2OTQyNTl9.SCVxQGM47l6j8_7vQcEedog-48Tu-bKa4GH2HtAGoEc");
        //saved token after login , if not valid token then use default token


        // Initialize authenticated API service
        apiService = ApiClient.getMovieService(token);
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }

    public void fetchMoviesFromApi() {
        // Get userId from shared prefs
        SharedPreferences prefs = application.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String userId = prefs.getString("user_id", "67ec63feaad418a657bd02da");
        // how to get userId from token

        if (userId == null) {
            System.err.println("❌ Missing user_id in SharedPreferences");
            return;
        }

        apiService.getAllMovies(userId).enqueue(new Callback<MovieCategoryResponse>() {
            @Override
            public void onResponse(Call<MovieCategoryResponse> call, Response<MovieCategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Map<String, List<Movie>> categorized = response.body().getMovies();

                    // Flatten all category movie lists
                    List<Movie> allMovies = new ArrayList<>();
                    for (List<Movie> categoryMovies : categorized.values()) {
                        allMovies.addAll(categoryMovies);
                    }

                    insertMovies(allMovies);
                }
            }

            @Override
            public void onFailure(Call<MovieCategoryResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void fetchAllCategories(Callback<List<Category>> callback) {
        apiService.getAllCategories().enqueue(callback);
    }

    public MovieApiService getApiService() {
        return apiService;
    }

    private void insertMovies(List<Movie> movies) {
        AsyncTask.execute(() -> movieDao.insertMovies(movies));
    }

    public void addMovie(Movie movie, Callback<ResponseBody> callback) {

        RequestBody titleBody = RequestBody.create(movie.getTitle(), MediaType.parse("text/plain"));
        RequestBody descriptionBody = RequestBody.create(movie.getDescription(), MediaType.parse("text/plain"));
        RequestBody releaseYearBody = RequestBody.create(String.valueOf(movie.getReleaseYear()), MediaType.parse("text/plain"));
        RequestBody durationBody = RequestBody.create(String.valueOf(movie.getDuration()), MediaType.parse("text/plain"));
        RequestBody castBody = RequestBody.create(String.join(",", movie.getCast()), MediaType.parse("text/plain"));
        List<MultipartBody.Part> categoryParts = new ArrayList<>();

        for (String catId : movie.getCategories()) {
            RequestBody catBody = RequestBody.create(catId, MediaType.parse("text/plain"));
            MultipartBody.Part part = MultipartBody.Part.createFormData("categories[]", null, catBody);
            categoryParts.add(part);
        }

        MultipartBody.Part movieFilePart = null;
        if (movie.getMovieFile() != null) {
            File movieFile = new File(movie.getMovieFile());
            RequestBody fileBody = RequestBody.create(movieFile, MediaType.parse("video/mp4"));
            movieFilePart = MultipartBody.Part.createFormData("movieFile", movieFile.getName(), fileBody);
        }
        MultipartBody.Part movieImagePart = null;
        if (movie.getMovieImage() != null) {
            File imageFile = new File(movie.getMovieImage());
            RequestBody imageBody = RequestBody.create(imageFile, MediaType.parse("image/*"));
            movieImagePart = MultipartBody.Part.createFormData("movieImage", imageFile.getName(), imageBody);
        }

        Call<ResponseBody> call = apiService.addMovie(
                titleBody,
                descriptionBody,
                releaseYearBody,
                durationBody,
                castBody,
                categoryParts,
                movieFilePart,
                movieImagePart
        );


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // לפני הכנסת הסרט ל־Room, אתחל את ה־_id אם הוא null
                    if (movie.get_id() == null || movie.get_id().isEmpty()) {
                        movie.set_id(UUID.randomUUID().toString());
                    }
                    AsyncTask.execute(() -> {
                        movieDao.insertMovies(Collections.singletonList(movie));
                    });
                }
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }


    public void deleteMovie(Movie movie) {
        AsyncTask.execute(() -> movieDao.deleteMovie(movie.get_id()));
    }
}