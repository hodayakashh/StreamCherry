import React, { useState, useRef } from "react";
import './MovieCard.css';
import MoviePopup from "../MoviePopup/MoviePopup";

function MovieCard({ src, title, description,releaseYear,rating ,duration, categories, cast, additionalMovies }) {
  const [isHovered, setIsHovered] = useState(false);
  const [isPopupOpen, setIsPopupOpen] = useState(false);
  const videoRef = useRef(null);

  const handleMouseEnter = () => {
    setIsHovered(true);
    if (videoRef.current) {
      videoRef.current.play();
    }
  };

  const handleMouseLeave = () => {
    setIsHovered(false);
    if (videoRef.current) {
      videoRef.current.pause();
      videoRef.current.currentTime = 0;
    }
  };

  const handlePlay = () => {
    alert("Play button clicked!");
    // Add functionality to play the video
  };

  const handleInfo = () => {
    setIsPopupOpen(true);
  };

  const handleClosePopup = () => {
    setIsPopupOpen(false);
  };

  return (
    <div 
      className="card" 
      onMouseEnter={handleMouseEnter} 
      onMouseLeave={handleMouseLeave}
    >
      <video
        ref={videoRef}
        src={src}
        className="card-video-top"
        muted
        loop
      />
      <div className="card-body">
        <div className="card-text">
          <p className="movie-title"> {title} </p>
          <button className="icon-button" onClick={handlePlay}>
            <i className="bi bi-play-circle-fill"></i>
          </button>
          <span className="movie-duration">{duration}</span>
          <button className="icon-button info" onClick={handleInfo}>
            <i className="bi bi-info-circle"></i>
          </button>
        </div>
      </div>
      {isPopupOpen && (
  <MoviePopup 
    src={src}
    title={title}
    description={description}    
    duration={duration}
    categories={categories}
    rating={rating}
    releaseYear={releaseYear}
    cast={cast}
    additionalMovies={additionalMovies}
    onClose={handleClosePopup}
    isOpen={isPopupOpen}
  />
)}
    </div>
  );
}

export default MovieCard;
