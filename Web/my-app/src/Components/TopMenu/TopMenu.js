import React, { useState } from "react";
import { Link } from "react-router-dom";
import NetflixLogo from "../NetflixLogo/NetflixLogo";
import TopMenuButton from "../TopMenuButton/TopMenuButton";
import SearchBar from "../SearchBar/SearchBar";
import "./TopMenu.css";
import ThemeToggle from "../ThemeToggle/ThemeToggle";
import ProfileIcon from "../ProfileIcon/ProfileIcon";
function TopMenu({LogOutSystem}) {


  return (
    <div className="top-menu">
      <nav className="navbar navbar-expand-lg navbar-dark">
        <div className="container-fluid">
          {/* Netflix Logo */}
          <Link to="/main" className="navbar-brand">
            <NetflixLogo />
          </Link>

          {/* Toggler button for small screens */}
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          {/* Navbar content */}
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <TopMenuButton dest="Home" />
              <TopMenuButton dest="Movies" />
              <TopMenuButton dest="admin-zone" />
              <TopMenuButton
               dest="Logout" 
                onClick={()=>LogOutSystem()}/>
            </ul>

            {/* Search Bar */}
            <SearchBar />

            {/* Profile Icon */}
            <ProfileIcon
              imagePath="/media/squirel.jpeg"
              altText="User Profile"
            />

            {/* Theme Toggle */}

            <ThemeToggle />
          </div>
        </div>
      </nav>
    </div>
  );
}

export default TopMenu;
