package com.example.androidapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.R;
import com.example.androidapp.entities.Movie;
import com.example.androidapp.viewmodels.MovieViewModel;
import com.example.androidapp.viewmodels.CategoryViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends AppCompatActivity {

    // שדות טופס
    private EditText editTextTitle, editTextDescription, editTextReleaseYear,
            editTextDuration, editTextCast, editTextDirector;
    private GridLayout gridLayoutCategories;
    private Button btnSaveMovie;

    // רשימת מזהי הקטגוריות שנבחרו (מעדכנים לפי בחירת המשתמש)
    private List<String> selectedCategoryIds = new ArrayList<>();

    private MovieViewModel movieViewModel;
    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        // Initialize UI components
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextReleaseYear = findViewById(R.id.editTextReleaseYear);
        editTextDuration = findViewById(R.id.editTextDuration);
        editTextCast = findViewById(R.id.editTextCast);
        gridLayoutCategories = findViewById(R.id.gridLayoutCategories);
        btnSaveMovie = findViewById(R.id.btnSaveMovie);

        // Initialize ViewModel
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);


        // Get categories from ViewModel and populate the GridLayout
        categoryViewModel.getCategories().observe(this, categories -> {
            gridLayoutCategories.removeAllViews();
            for (com.example.androidapp.entities.Category category : categories) {
                // Inflate של layout מותאם אישית ל-checkbox
                View checkboxItem = LayoutInflater.from(this)
                        .inflate(R.layout.checkbox_category_item, gridLayoutCategories, false);

                CheckBox checkBox = checkboxItem.findViewById(R.id.checkBoxCategory);
                checkBox.setText(category.getName());

                checkBox.setTag(category.getServerId());


                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    String catId = buttonView.getTag().toString();
                    if (isChecked) {
                        if (!selectedCategoryIds.contains(catId)) {
                            selectedCategoryIds.add(catId);
                        }
                    } else {
                        selectedCategoryIds.remove(catId);
                    }
                });

                gridLayoutCategories.addView(checkboxItem);
            }
        });

        // Listener לכפתור שמירה
        btnSaveMovie.setOnClickListener(v -> createAndAddMovie());
    }

    private void createAndAddMovie() {
        // קריאת נתונים מהשדות
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String releaseYearStr = editTextReleaseYear.getText().toString().trim();
        String durationStr = editTextDuration.getText().toString().trim();
        String castStr = editTextCast.getText().toString().trim();

        if(title.isEmpty() || releaseYearStr.isEmpty() || durationStr.isEmpty()){
            Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int releaseYear = Integer.parseInt(releaseYearStr);
        int duration = Integer.parseInt(durationStr);

        // המרת שדה "cast" לרשימה - מניחים שהמשתמש מפריד בין שחקנים באמצעות פסיק
        List<String> castList = new ArrayList<>();
        if (!castStr.isEmpty()) {
            castList = Arrays.asList(castStr.split("\\s*,\\s*"));
        }


        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setReleaseYear(releaseYear);
        movie.setDuration(duration);
        movie.setCast(castList);
        movie.setCategories(selectedCategoryIds);


        movieViewModel.addMovie(movie, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    runOnUiThread(() -> {
                        Toast.makeText(AddMovieActivity.this, "Movie added successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                } else {
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "null";
                        Log.e("AddMovieActivity", "Error: " + response.code() + " " + errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }                    runOnUiThread(() -> {
                        Toast.makeText(AddMovieActivity.this, "Failed to add movie: " + response.code(), Toast.LENGTH_SHORT).show();
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                runOnUiThread(() -> {
                    Toast.makeText(AddMovieActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}
