package com.example.androidapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    private String name;
    private int [] movies;
    private boolean promoted;

    public Category() {
    }
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
