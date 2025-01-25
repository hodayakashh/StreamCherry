import React, { useState, useEffect } from "react";

function DeleteMovie() {
  const [movieName, setMovieName] = useState(""); // User-entered movie name
  const [movies, setMovies] = useState([]); // List of all movies from the server
  const [filteredMovies, setFilteredMovies] = useState([]); // Filtered movies based on query
  const [message, setMessage] = useState(""); // Message to the user

  useEffect(() => {
    // Fetch the list of movies when the component mounts
    const fetchMovies = async () => {
      try {
        const response = await fetch("http://localhost:3000/api/movies");
        const data = await response.json();
        console.log("Movies fetched:", data);
        setMovies(data); // Store the full list of movies
        alert("Movies fetched successfully!");
      } catch (error) {
        setMessage(`Failed to fetch movies: ${error.message}`);
      }
    };

    fetchMovies();
  }, []);

  const handleSearch = (e) => {
    const query = e.target.value;
    setMovieName(query);

    // Filter movies by title based on the query
    const filtered = movies.filter((movie) =>
      movie.title.toLowerCase().includes(query.toLowerCase())
    );
    setFilteredMovies(filtered);
  };

  const handleDelete = async (movieId) => {
    try {
      // Send DELETE request to the server
      const deleteResponse = await fetch(`http://foo.com/api/movies/${movieId}`, {
        method: "DELETE",
      });

      if (deleteResponse.ok) {
        setMessage("Movie deleted successfully!");

        // Update the list of movies after deletion
        setMovies((prevMovies) => prevMovies.filter((movie) => movie._id !== movieId));
        setFilteredMovies((prevFiltered) =>
          prevFiltered.filter((movie) => movie._id !== movieId)
        );
      } else {
        const errorData = await deleteResponse.json();
        setMessage(`Failed to delete movie: ${errorData.error}`);
      }
    } catch (error) {
      setMessage(`Server error: ${error.message}`);
    }
  };

  return (
    <div className="modal">
      <h2>Delete Movie</h2>
      <div>
        <label htmlFor="movieName">Search Movie by Title:</label>
        <input
          type="text"
          id="movieName"
          name="movieName"
          value={movieName}
          onChange={handleSearch} // Update query and filter movies
          placeholder="Enter movie title"
        />
      </div>
      {filteredMovies.length > 0 ? (
        <ul className="movies-list">
          {filteredMovies.map((movie) => (
            <li key={movie._id}>
              <span>{movie.title}</span>
              <button
                className="btn btn-danger"
                onClick={() => handleDelete(movie._id)}
              >
                Delete
              </button>
            </li>
          ))}
        </ul>
      ) : (
        movieName && <p>No matching movies found.</p>
      )}
      {message && <p>{message}</p>} {/* Display messages */}
    </div>
  );
}

export default DeleteMovie;
