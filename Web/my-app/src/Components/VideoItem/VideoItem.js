import React from "react";
import "./VideoItem.css";
import PlayButton from '../PlayButton/PlayButton';
import InfoButton from '../InfoButton/InfoButton';

function VideoItem(){
     return (
    <div className="Video-item">
      <video autoPlay muted loop className="video">
        <source  src="/media/nature.mp4" type="video/mp4" />
      </video>
      <div className = "video-item-content">
        <h1>Recursive Waterfall </h1>
        <p>Where nature's flow meets infinite patterns</p>
        <div className="button-container">
          <PlayButton onClick={() => console.log('Play clicked')} />
          <InfoButton onClick={() => console.log('Info clicked')} />
        </div>
      </div>
    </div>
  );
}

export default VideoItem;