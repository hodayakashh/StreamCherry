// Java
// File: app/src/main/java/com/example/androidapp/entities/Category.java
package com.example.androidapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Category {


    @PrimaryKey
    private int id;
    @NonNull
    private String name;
    @Ignore
    private int[] movies;
    private boolean promoted;

    // No-arg constructor that Room will use
    public Category() {
    }

    // Parameterized constructor for convenience - not used by Room
    @Ignore
    public Category(String name, boolean promoted) {
        this.name = name;
        this.promoted = promoted;
    }

    // Another parameterized constructor for convenience - not used by Room
    @Ignore
    public Category(String name, int[] movies, boolean promoted) {
        this.name = name;
        this.movies = movies;
        this.promoted = promoted;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public int[] getMovies() {
        return movies;
    }

    public void setMovies(int[] movies) {
        this.movies = movies;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}