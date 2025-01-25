import React, { useState } from "react";
import FormInput from "../../Components/SignUpComponents/FormInput";
import "./AddCategory.css";

function AddCategory({ toggleAddCategoryModal, handleAddCategorySubmit }) {
  const [formData, setFormData] = useState({
    name: "",
    promoted: true,
    movies: [], // Default empty array
  });

  const [isSubmitting, setIsSubmitting] = useState(false); // To manage submission state

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: name === "promoted" ? value === "true" : value,
    });
  };

  const handleMoviesChange = (e) => {
    const movieIds = e.target.value.split(",").map((id) => id.trim()); // Split and trim movie IDs
    setFormData({
      ...formData,
      movies: movieIds,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true); // Disable the form during submission

    // Prepare the category object
    const categoryData = {
      name: formData.name,
      movies: formData.movies,
      promoted: formData.promoted,
    };

    try {
      const response = await fetch("http://localhost:3000/api/categories", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(categoryData),
      });

      const data = await response.json();
      if (response.ok) {
        alert("Category Successfully Added!");
        toggleAddCategoryModal(); // Close the modal
      } else {
        alert("Error adding category: " + (data.error || "Unknown error"));
      }
    } catch (error) {
    
      alert("Server Error: " + error.message);
    } finally {
      setIsSubmitting(false); // Re-enable the form
    }
  };

  return (
    <div className="modal">
      <h2>Add New Category</h2>
      <form onSubmit={handleSubmit}>
        <FormInput
          label="Category Name"
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
          required
        />
        {/* Dropdown for Promoted Field */}
        <div className="form-group">
          <label htmlFor="promoted">Promoted:</label>
          <select
            id="promoted"
            name="promoted"
            value={formData.promoted ? "true" : "false"}
            onChange={handleChange}
          >
            <option value="true">True</option>
            <option value="false">False</option>
          </select>
        </div>
        {/* Movies Field */}
        <FormInput
          label="Movie IDs (comma-separated)"
          type="text"
          name="movies"
          value={formData.movies.join(", ")}
          onChange={handleMoviesChange}
        />
        <div className="modal-actions">
          <button
            type="submit"
            className="btn btn-primary"
            disabled={isSubmitting} // Disable the button while submitting
          >
            {isSubmitting ? "Adding..." : "Add Category"}
          </button>
          <button
            type="button"
            className="btn btn-secondary"
            onClick={toggleAddCategoryModal}
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
}

export default AddCategory;
