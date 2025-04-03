package com.example.androidapp;

import com.example.androidapp.activities.TokenInterceptor;
import com.example.androidapp.api.MovieApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:3000/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(String token) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(token))
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static MovieApiService getMovieService(String token) {
        return getRetrofit(token).create(MovieApiService.class);
    }

}