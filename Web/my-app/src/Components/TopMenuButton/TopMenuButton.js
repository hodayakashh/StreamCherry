import React from "react";
import { Link } from "react-router-dom";

function TopMenuButton({ dest, onClick }) {
  const handleClick = (e) => {
    if (dest.toLowerCase() === "logout") {
      e.preventDefault(); // Prevent default navigation for logout
      onClick(); // Call the onClick event for logout
    }
  };

  return (
    <li className="nav-item">
      <Link
        to={dest.toLowerCase() === "logout" ? "#" : `/${dest.toLowerCase()}`} // Use '#' if dest is logout
        className="nav-link active"
        aria-current="page"
        onClick={handleClick}
      >
        {dest}
      </Link>
    </li>
  );
}

export default TopMenuButton;
