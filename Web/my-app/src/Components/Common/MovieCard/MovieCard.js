import React, { useState, useEffect } from 'react';
import './MovieCard.css';
import { useNavigate } from 'react-router-dom';
import MoviePopup from 'Components/MoviePage/MoviePopup/MoviePopup';
import tokenVerification from 'tokenVerification/tokenVerification';
const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:3001';
function MovieCard({ movie }) {
  const [imageError, setImageError] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [isHovered, setIsHovered] = useState(false);
  const [isPopupOpen, setIsPopupOpen] = useState(false);
  const [hoverTimer, setHoverTimer] = useState(null);
  const defaultImage = "/media/images/avatars/avatar1.webp"; // Default image path
  const navigate = useNavigate();

  const getImageUrl = () => {
    if (!movie || !movie.movieImage || imageError) {
      return defaultImage;
    }
    return `${API_BASE_URL}/${movie.movieImage}`;
  };

  // Cleanup hover timer on component unmount
  useEffect(() => {
    return () => {
      if (hoverTimer) {
        clearTimeout(hoverTimer);
      }
    };
  }, [hoverTimer]);

  // Handle mouse enter with a delay
  const handleMouseEnter = () => {
    const timer = setTimeout(() => {
      setIsHovered(true);
      setIsPopupOpen(true);
    }, 1500); // Wait for 1 second before triggering
    setHoverTimer(timer);
  };

  // Handle mouse leave and clear the timer
  const handleMouseLeave = () => {
    if (hoverTimer) {
      clearTimeout(hoverTimer); // Clear the timer if the mouse leaves before 1 second
    }
    setIsHovered(false);
  };

  // Handle play button click
  const handlePlay = async () => {
    try {
      const token = localStorage.getItem("jwtToken");
      if (!token) {
        console.error("Token not found.");
        return;
      }

      const userData = await tokenVerification(token);
      if (!userData) {
        console.error("User data verification failed.");
        return;
      }

      const response = await fetch(
        `${API_BASE_URL}/api/movies/${movie._id}/recommend/`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
            userId: userData._id,
          },
          body: JSON.stringify({ userId: userData._id }),
        }
      );

      const data = await response.json();
      navigate(`/movie/${movie._id}`, { state: { movie } });
    } catch (error) {
      console.error("Error during handlePlay:", error.message);
    }
  };

  // Close the popup
  const handleClosePopup = () => {
    setIsPopupOpen(false);
  };

  return (
    <div
      className={`movie-card-poster ${isHovered ? 'hovered' : ''}`}
      onMouseEnter={handleMouseEnter}
      onMouseLeave={handleMouseLeave} // Ensure the timer is cleared on mouse leave
    >
      <img
        src={getImageUrl()}
        className={`card-img-top ${isLoading ? 'loading' : ''}`}
        alt={movie?.title}
        onError={() => {
          setImageError(true);
          setIsLoading(false);
        }}
        onLoad={() => setIsLoading(false)}
      />
      {isPopupOpen && (
        <MoviePopup
          movie={movie}
          onClose={handleClosePopup}
          isOpen={isPopupOpen}
        />
      )}
    </div>
  );
}

export default MovieCard;