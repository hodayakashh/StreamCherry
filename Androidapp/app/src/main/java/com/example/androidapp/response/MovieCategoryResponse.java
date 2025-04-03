package com.example.androidapp.response;

import com.example.androidapp.entities.Movie;

import java.util.List;
import java.util.Map;

public class MovieCategoryResponse {
    private Map<String, List<Movie>> movies;

    public Map<String, List<Movie>> getMovies() {
        return movies;
    }
}