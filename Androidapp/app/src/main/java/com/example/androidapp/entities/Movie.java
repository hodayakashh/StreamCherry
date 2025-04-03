package com.example.androidapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String _id;
    private String title;
    private String description;
    private int releaseYear;
    private int duration;
    private List<String> categories;
    private double rating;
    private List<String> cast;
    private String movieFile;
    private String movieImage;
    private Date createdAt;
    private List<String> watchedBy;

    public Movie() {}

    // Getters
    public String get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getDuration() {
        return duration;
    }

    public List<String> getCategories() {
        return categories;
    }

    public double getRating() {
        return rating;
    }

    public List<String> getCast() {
        return cast;
    }

    public String getMovieFile() {
        return movieFile;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<String> getWatchedBy() {
        return watchedBy;
    }

    // Setters
    public void set_id(String _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public void setMovieFile(String movieFile) {
        this.movieFile = movieFile;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setWatchedBy(List<String> watchedBy) {
        this.watchedBy = watchedBy;
    }
}