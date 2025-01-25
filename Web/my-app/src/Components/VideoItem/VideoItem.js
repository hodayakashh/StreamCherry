import React from "react";
function VideoItem(){
     return (
    <div className="Video-item">
      <video autoPlay muted loop className="video">
        <source  src="/media/squirel.mp4" type="video/mp4" />
      </video>
    </div>
  );
}

export default VideoItem;