import React from "react";
import "./InfoButton.css";
function InfoButton({ onClick }) {
    return (
      <button className="info-btn" onClick={onClick}>
      <img src="/media/Buttons/InfoButton.svg" type="image/svg" alt="Info" className="Info-icon" />
      </button>
    );
  }
  
  export default InfoButton;
  