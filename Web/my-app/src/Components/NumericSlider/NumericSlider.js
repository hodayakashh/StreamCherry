import React, { useRef } from "react";
import MovieCard from "../MovieCard/MovieCard";
import "./NumericSlider.css";

function NumericSlider({ title,movies}) {
  const sliderRef = useRef(null);

  const scrollLeft = () => {
    if (sliderRef.current) {
      sliderRef.current.scrollBy({ left: -320, behavior: "smooth" }); // Adjust scroll distance based on card width
    }
  };

  const scrollRight = () => {
    if (sliderRef.current) {
      sliderRef.current.scrollBy({ left: 320, behavior: "smooth" }); // Adjust scroll distance based on card width
    }
  };

  return (
    <div className="numeric-slider">
      {/* Title */}
      <h2 className="numeric-title">{title}</h2>

      {/* Arrow Controls */}
      <button className="arrow-numeric left-arrow-numeric" onClick={scrollLeft}>
        &lt;
      </button>
      <div className="slider-numeric-container" ref={sliderRef}>
        {movies.map((movie, index) => (
          <div className="item-container" key={index}>
            <img src={`/media/Numbers/${index + 1}.png`} className="number-icon" alt={`Number ${index + 1}`} />
            <MovieCard 
              key={index} 
              src={movie.src} 
              title={movie.title} 
              releaseYear={movie.releaseYear}
              rating={movie.rating}
              description={movie.description} 
              duration={movie.duration} 
              categories={movie.categories} 
              cast={movie.cast} 
              additionalMovies={movie.additionalMovies} 
/>          </div>
        ))}
      </div>
      <button className="arrow-numeric right-arrow-numeric" onClick={scrollRight}>
        &gt;
      </button>
    </div>
  );
}

export default NumericSlider;
