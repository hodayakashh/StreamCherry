// Java
// File: app/src/main/java/com/example/androidapp/entities/Category.java
package com.example.androidapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Category {


    @PrimaryKey (autoGenerate = true)
    private int id;

    @SerializedName("_id")
    private String ServerId;
    @NonNull
    private String name;
    @Ignore
    private transient Movie[] movies;
    private boolean promoted;

    private boolean isSelected;

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
    public Category(String name, Movie [] movies, boolean promoted) {
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Movie[] getMovies() {
        return movies;
    }

    public void setMovies(Movie[] movies) {
        this.movies = movies;
    }

    public String getServerId() {
        return ServerId;
    }

    public void setServerId(String serverId) {
        ServerId = serverId;
    }
}