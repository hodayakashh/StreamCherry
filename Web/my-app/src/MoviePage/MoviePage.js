import React from "react";
import MovieView from "../Components/MovieView/MovieView";
const MoviePage = () => {
  return (
    <div>
      <MovieView 
        videoSrc="/media/Animals-trending/pinguin.mp4" 
        movieTitle="pinguin" 
      />
    </div>
  );
};
export default MoviePage;
