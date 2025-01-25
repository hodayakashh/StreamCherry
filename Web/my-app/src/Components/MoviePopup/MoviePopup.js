import React from "react";
import ReactDOM from "react-dom";
import "./MoviePopup.css";
import SmallMovieInfo from "../SmallMovieInfo/SmallMovieInfo";

const MoviePopup = ({ src, title, description, releaseYear, rating, duration, categories, cast, additionalMovies, onClose }) => {
  return ReactDOM.createPortal(
    <div className="popup-overlay" onClick={onClose}>
      <div className="popup-content" onClick={(e) => e.stopPropagation()}>
        <button className="close-btn" onClick={onClose}>X</button>

        {/* Video Section */}
        <video className="popup-video" controls>
          <source src={src} type="video/mp4" />
        </video>

        {/* Details Section */}
        <div className="popup-details">

          {/* Left section */}
          <div className="popup-left">
            {/* Title Section */}
            <div className="popup-header">
              <h2>{title}</h2>
            </div>
            {/* Additional info */}
            <div className="popup-additional-info">
              <p>{releaseYear}</p>
              <p>{duration}</p>
              <p className="rating">{rating}</p> {/* Add class to rating */}
            </div>

            {/* Description Section */}
            <div className="popup-description">
              <p>{description}</p>
            </div>
          </div>

          {/* Right section */}
          <div className="popup-right">
            <div className="popup-cast">
              <p>
                <span className="cast-title">Cast: </span>
                {cast.map((actor, index) => (
                  <span key={index} className="actor-name">
                    {actor}
                    {index < cast.length - 1 && ", "}
                  </span>
                ))}
              </p>
            </div>
            <div className="popup-categories">
              <p>
                <span className="categories-title">Categories: </span>
                {categories.map((category, index) => (
                  <span key={index} className="category-name">
                    {category}
                    {index < categories.length - 1 && ", "}
                  </span>
                ))}
              </p>
            </div>
          </div>
        </div>

        {/* Additional Movies Section */}
        <div className="popup-additional-movies">
          <h3>More like this</h3>
          <SmallMovieInfo movies={additionalMovies} />
        </div>
      </div>
    </div>,
    document.getElementById("popup-root")
  );
};

export default MoviePopup;
