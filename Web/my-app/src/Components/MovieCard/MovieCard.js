import React from "react";
import './MovieCard.css';

function MovieCard({movie}) {
  const handlePlay = () => {
    alert("Play button clicked!");
    // Add functionality to play the video
  };

  const handleLike = () => {
    alert("Like button clicked!");
    // Add functionality to like the movie
  };

  const handleInfo = () => {
    alert("Info button clicked!");
    // Add functionality to show more info about the movie
  };

  return (
    <div className="card">
      <img src="/media/squirel.jpeg" className="card-img-top" alt="..." />
      <div className="card-body">
        <div className="card-text">
          <button className="icon-button" onClick={handlePlay}>
            <i className="bi bi-play-circle-fill"></i>
          </button>
          <button className="icon-button" onClick={handleLike}>
            <i className="bi bi-hand-thumbs-up"></i>
          </button>
          <button className="icon-button" onClick={handleInfo}>
            <i className="bi bi-info-circle"></i>
          </button>
        </div>
      </div>
    </div>
  );
}

export default MovieCard;
