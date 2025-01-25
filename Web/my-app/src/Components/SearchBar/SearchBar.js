import { useRef, useEffect } from "react";
import { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";

function SearchBar() {
  const [showSearch, setShowSearch] = useState(false); // State to toggle search bar
  const [queryValue, setQueryValue] = useState(""); // State to hold query value
  const navigate = useNavigate(); // Hook for navigation
  const location = useLocation(); // Get current location
  const inputRef = useRef(null);
  const containerRef = useRef(null); // Ref for the search container

  useEffect(() => {
    // Automatically show the search bar if on a search page
    if (location.pathname.startsWith("/search")) {
      setShowSearch(true);
      const query = location.pathname.split("/search/")[1] || ""; // Extract query from URL
      setQueryValue(decodeURIComponent(query)); // Set query state
    }
  }, [location]);

  useEffect(() => {
    if (inputRef.current) {
      inputRef.current.value = queryValue; // Sync input value with query state
    }
  }, [queryValue]);

  const toggleSearch = () => {
    setShowSearch(!showSearch); // Toggle the search bar
  };

  const handleInputChange = () => {
    if (inputRef.current) {
      const value = inputRef.current.value.trim(); // Trim spaces
      setQueryValue(value); // Update query state
      if (value) {
        navigate(`/search/${value}`); // Navigate to search page with the query
      } else {
        navigate("/main"); // Navigate to the main page if the input is empty
      }
    }
  };

  const handleClickOutside = (event) => {
    if (
      containerRef.current &&
      !containerRef.current.contains(event.target) && // Check if click is outside
      queryValue === "" // Only close if input is empty
    ) {
      setShowSearch(false); // Close search bar if clicked outside
    }
  };

  useEffect(() => {
    if (showSearch) {
      document.addEventListener("mousedown", handleClickOutside); // Add event listener on click
    } else {
      document.removeEventListener("mousedown", handleClickOutside); // Clean up
    }

    return () => {
      document.removeEventListener("mousedown", handleClickOutside); // Clean up on unmount
    };
  }, [showSearch, queryValue]);

  return (
    <div
      ref={containerRef}
      className={`search-container ${showSearch ? "expanded" : ""}`}
    >
      {!showSearch ? (
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="20"
          height="20"
          fill="currentColor"
          className="bi bi-search"
          viewBox="0 0 16 16"
          onClick={toggleSearch}
          style={{ cursor: "pointer", color: "var(--text-color)" }}
        >
          <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
        </svg>
      ) : (
        <div className="search-bar">
          <input
            ref={inputRef}
            className="form-control"
            type="search"
            placeholder="Title, People, Genres"
            aria-label="Search"
            autoFocus
            onKeyUp={handleInputChange} // Trigger navigation on input
          />
        </div>
      )}
    </div>
  );
}

export default SearchBar;
