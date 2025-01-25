import React from "react";
import "./SmallMovieInfo.css";

const SmallMovieInfo = ({ movies }) => {
  return (
    <div className="movie-grid">
      {movies.map((movie, index) => (
        <div key={index} className="movie-card">
          <img
            src={movie.src}
            alt={movie.title}
            className="movie-image"
            width="230"
            height="130"
          />
          <h4 className="movie-title">{movie.title}</h4>
          <p className="movie-description">{movie.description}</p>
        </div>
      ))}
    </div>
  );
};

export default SmallMovieInfo;
