import React from "react";
import MovieCard from "../MovieCard/MovieCard";
import "./SearchResults.css";

function SearchResults({ movies }) {
  return (
    <div className="search-results-container">
      {movies.length > 0 ? (
        <>
          <h1>Search Results</h1>
          <div className="movie-grid">
            {movies.map((movie, key) => (
              <MovieCard key={key} movie={movie} />
            ))}
          </div>
        </>
      ) : (
        <div className="no-results-message">
          <h1>No Results Found</h1>
          <p>Try searching for a different title, person, or genre.</p>
        </div>
      )}
    </div>
  );
}

export default SearchResults;
