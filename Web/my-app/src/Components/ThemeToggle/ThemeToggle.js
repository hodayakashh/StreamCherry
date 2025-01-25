import React, { useState, useEffect } from "react";
import "./ThemeToggle.css";

function ThemeToggle() {
  const [isLightMode, setIsLightMode] = useState(false);

  // Load theme from localStorage when the component mounts
  useEffect(() => {
    const savedTheme = localStorage.getItem("theme") || "dark-mode"; // Default to dark mode
    setIsLightMode(savedTheme === "light-mode");
    document.body.className = savedTheme;
  }, []);

  const toggleTheme = () => {
    const newTheme = isLightMode ? "dark-mode" : "light-mode";
    setIsLightMode(!isLightMode);
    document.body.className = newTheme;
    localStorage.setItem("theme", newTheme); // Save theme in localStorage
  };

  return (
    <div className="theme-toggle">
      <label className="switch">
        <input
          type="checkbox"
          checked={isLightMode}
          onChange={toggleTheme}
        />
        <span className="slider round"></span>
      </label>
      <span className="theme-label">Light Mode</span>
    </div>
  );
}

export default ThemeToggle;
