import React, { useState } from "react";
import FormInput from "../../Components/SignUpComponents/FormInput";
import FileInput from "../../Components/SignUpComponents/FileInput";
import useCategories from "../../assets/useCategories";

function AddMovie({ toggleAddMovieModal, handleAddMovieSubmit }) {
  const categories = useCategories(); // Fetch categories using the custom hook

  const [formData, setFormData] = useState({
    title: "",
    description: "",
    releaseYear: "",
    duration: "",
    categories: [], // רשימת הקטגוריות שנבחרו
    movieFile: null,
    cast: "",
    director: "",
  });

  // פונקציה לעדכון קטגוריות
  const handleCategoryChange = (e) => {
    const { value, checked } = e.target;

    setFormData((prevFormData) => {
      const updatedCategories = checked
        ? [...prevFormData.categories, value] // הוספת קטגוריה
        : prevFormData.categories.filter((cat) => cat !== value); // הסרת קטגוריה

      console.log("Updated categories:", updatedCategories); // Debug: בדוק את הרשימה המעודכנת
      return {
        ...prevFormData,
        categories: updatedCategories,
      };
    });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData({
      ...formData,
      [name]:
        name === "releaseYear" || name === "duration" ? Number(value) : value,
    });
  };

  const handleFileChange = (e) => {
    const { name, files } = e.target;
    setFormData({
      ...formData,
      [name]: files[0], // Store the selected file
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Movie metadata
    const movieJson = {
      title: formData.title,
      description: formData.description,
      releaseYear: formData.releaseYear,
      duration: formData.duration,
      categories: formData.categories,
      cast: formData.cast,
      director: formData.director,
    };

    console.log("Submitting movie data:", movieJson); // Debug: בדיקה לפני שליחה

    try {
      const response = await fetch("http://localhost:3000/api/movies", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(movieJson),
      });

      const data = await response.json();
      if (response.ok) {
        alert("Movie Successfully Added!");
        toggleAddMovieModal(); // Close the modal
      } else {
        alert("Error adding movie: " + (data.error || "Unknown error"));
      }
    } catch (error) {
      alert("Server Error: " + error.message);
    }
  };

  return (
    <div className="modal">
      <h2>Add New Movie</h2>
      <form onSubmit={handleSubmit}>
        <FormInput
          label="Movie Title"
          type="text"
          name="title"
          value={formData.title}
          onChange={handleChange}
        />
        <FormInput
          label="Description"
          type="textarea"
          name="description"
          value={formData.description}
          onChange={handleChange}
        />
        <FormInput
          label="Release Year"
          type="number"
          name="releaseYear"
          value={formData.releaseYear}
          onChange={handleChange}
        />
        <FormInput
          label="Duration"
          type="number"
          name="duration"
          value={formData.duration}
          onChange={handleChange}
        />

        <FileInput
          label="Movie File (MP4)"
          name="movieFile"
          onChange={handleFileChange}
          accept="video/mp4"
        />
        <FormInput
          label="Cast"
          type="text"
          name="cast"
          value={formData.cast}
          onChange={handleChange}
        />
        <FormInput
          label="Director"
          type="text"
          name="director"
          value={formData.director}
          onChange={handleChange}
        />
        <div>
          <label>Categories</label>
          <div>
            {/* בדיקה אם יש קטגוריות */}
            {Array.isArray(categories) && categories.length > 0 ? (
              categories.map((category) => (
                <div key={category.id} className="form-check">
                  <input
                    className="form-check-input"
                    type="checkbox"
                    id={`category-${category.id}`}
                    value={category._id}
                    checked={formData.categories.includes(category._id)} // בדיקה אם הקטגוריה קיימת
                    onChange={handleCategoryChange} // קריאה לפונקציית שינוי
                  />
                  <label
                    className="form-check-label"
                    htmlFor={`category-${category.id}`}
                  >
                    {category.name}
                  </label>
                </div>
              ))
            ) : (
              <p>No categories available.</p>
            )}
          </div>
        </div>
        <div className="modal-actions">
          <button type="submit" className="btn btn-primary">
            Add Movie
          </button>
          <button
            type="button"
            className="btn btn-secondary"
            onClick={toggleAddMovieModal}
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
}

export default AddMovie;
