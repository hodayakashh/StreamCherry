package com.example.androidapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.MyApplication;
import com.example.androidapp.entities.Movie;
import com.example.androidapp.repositories.MovieRepository;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Callback;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private MutableLiveData<List<Movie>> movies;
    private MyApplication app = MyApplication.getInstance();

    public MovieViewModel() {
        // Initialize the repository with application context
        movieRepository = new MovieRepository(app);
        movies = new MutableLiveData<>();
        // Set initial value from repository's LiveData
        movies.setValue(movieRepository.getAllMovies().getValue());
    }

    // Expose the movies as immutable LiveData
    public LiveData<List<Movie>> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    // Trigger fetching movies from the API and updating the database
    public void fetchMoviesFromApi() {
        movieRepository.fetchMoviesFromApi();
    }

    public void addMovie(Movie movie, Callback <ResponseBody> callback) {
        movieRepository.addMovie(movie, callback);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.deleteMovie(movie);
    }
}
