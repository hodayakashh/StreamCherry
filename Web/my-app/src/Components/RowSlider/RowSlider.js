import React, { useRef } from "react";
import MovieCard from "../MovieCard/MovieCard";
import "./RowSlider.css";

function RowSlider({ title, movies}) {
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
    <div className="row-slider">
      {/* Title */}
      <h2 className="row-title">{title}</h2>

      {/* Arrow Controls */}
      <button className="arrow left-arrow" onClick={scrollLeft}>
        &lt;
      </button>
      <div className="slider-container" ref={sliderRef}>
        {movies.map((movie, index) => (
          <MovieCard key={index} src={movie.src} title={movie.title} duration={movie.duration} />
        ))}
      </div>
      <button className="arrow right-arrow" onClick={scrollRight}>
        &gt;
      </button>
    </div>
  );
}

export default RowSlider;
