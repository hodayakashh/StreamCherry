import React from "react";
import "./PlayButton.css";
function PlayButton({ onClick }) {
    return (
      <button className="play-btn" onClick={onClick}>
      <img src="/media/Buttons/PlayButton.svg" type="image/svg" alt="Play" className="play-icon" />
      </button>
    );
  }
  
  export default PlayButton;
  