package com.example.androidapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AdminActivity extends AppCompatActivity {

    private View actionsList;
    private Button toggleActionsButton;
    private boolean isActionsVisible = false;
    private String activePanel = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Initialize views
        actionsList = findViewById(R.id.actionsList);
        toggleActionsButton = findViewById(R.id.toggleActionsButton);

        // Toggle actions visibility
        toggleActionsButton.setOnClickListener(v -> toggleActions());

        setActions();

    }

    private void toggleActions() {
        isActionsVisible = !isActionsVisible;
        actionsList.setVisibility(isActionsVisible ? View.VISIBLE : View.GONE);
        toggleActionsButton.setText(isActionsVisible ? R.string.hide_actions : R.string.show_actions);
    }


    private void setActions() {
        // Add movie button
        Button addMovieButton = findViewById(R.id.addMovieButton);
        addMovieButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, AddMovieActivity.class);
            startActivity(intent);
        });

        // Edit movie button
        Button editMovieButton = findViewById(R.id.editMovieButton);
        editMovieButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, EditMovieActivity.class);
            startActivity(intent);
        });

        // Delete movie button
        Button deleteMovieButton = findViewById(R.id.deleteMovieButton);
        deleteMovieButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, DeleteMovieActivity.class);
            startActivity(intent);
        });
        // Category management button
        Button categoryManagementButton = findViewById(R.id.categoryManagementButton);
        categoryManagementButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, CategoryManagementActivity.class);
            startActivity(intent);
        });
    }
}
