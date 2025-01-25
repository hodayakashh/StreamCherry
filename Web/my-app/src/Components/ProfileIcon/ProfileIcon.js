import React from "react";
import PropTypes from "prop-types";
import "./ProfileIcon.css";

function ProfileIcon({ imagePath, altText }) {
  return (
    <div className="profile-icon">
      <img
        src={imagePath}
        alt={altText}
        className="profile-img"
      />
    </div>
  );
}

ProfileIcon.propTypes = {
  imagePath: PropTypes.string.isRequired, // Path to the image
  altText: PropTypes.string.isRequired, // Alt text for accessibility
};

export default ProfileIcon;
