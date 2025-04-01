// Java
package com.example.androidapp.api;

import com.example.androidapp.R;
import com.example.androidapp.MyApplication;
import com.example.androidapp.entities.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryApi {

    Retrofit retrofit;
    ApiService apiService;

    public CategoryApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void getCategories() {
        Call<List<Category>> call = apiService.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categories = response.body();
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });

    }
}