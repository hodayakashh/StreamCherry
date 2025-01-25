import React, { useState } from "react";
import TopMenu from "../Components/TopMenu/TopMenu";
import AddMovie from "../Components/AddMovie/AddMovie";
import AddCategory from "../Components/AddCategory/AddCategory";
import DeleteMovie from "../Components/DeleteMovie/DeleteMovie";
import "./AdminPage.css";

function AdminPage() {
  const [activeModal, setActiveModal] = useState(null); // Manage which modal is open

  const toggleModal = (modalName) => {
    setActiveModal((prevModal) => (prevModal === modalName ? null : modalName));
  };

  const handleAddMovieSubmit = (e) => {
    e.preventDefault();
    alert("Movie added!");
    setActiveModal(null); // Close modal after submission
  };

  const handleAddCategorySubmit = (e) => {
    e.preventDefault();
    alert("Category added!");
    setActiveModal(null); // Close modal after submission
  };

  const handleDeleteMovieSubmit = (movieId) => {
    alert(`Movie with ID ${movieId} deleted!`);
    setActiveModal(null); // סגור מודל לאחר השליחה
  };

  return (
    <div className="admin-page">
      <TopMenu />
      <div className="admin-page-header">
        <h1>Admin Zone</h1>
        <p>Welcome to the admin zone! Perform your actions here.</p>
      </div>

      <div className="admin-actions">
        <button
          className="toggle-actions-btn"
          onClick={() => toggleModal("actions")}
        >
          {activeModal === "actions" ? "Hide Actions" : "Show Actions"}
        </button>
        {activeModal === "actions" && (
          <ul className="actions-list">
            <li onClick={() => toggleModal("addMovie")}>Add Movie</li>
            <li>Edit Movie</li>
            <li onClick={() => toggleModal("addCategory")}>Add Category</li>
            <li onClick={() => toggleModal("deleteMovie")}>Delete Movie</li>
          </ul>
        )}
      </div>

      {/* Add Movie Modal */}
      {activeModal === "addMovie" && (
        <div className="modal-overlay">
          <AddMovie
            toggleAddMovieModal={() => toggleModal("addMovie")}
            handleAddMovieSubmit={handleAddMovieSubmit}
          />
        </div>
      )}

      {/* Add Category Modal */}
      {activeModal === "addCategory" && (
        <div className="modal-overlay">
          <AddCategory
            toggleAddCategoryModal={() => toggleModal("addCategory")}
            handleAddCategorySubmit={handleAddCategorySubmit}
          />
        </div>
      )}

      {/* Delete Movie Modal */}
      {activeModal === "deleteMovie" && (
        <div className="modal-overlay">
          <DeleteMovie
            toggleDeleteMovieModal={() => toggleModal("deleteMovie")}
            handleDeleteMovieSubmit={handleDeleteMovieSubmit}
          />
        </div>
      )}
    </div>
  );
}

export default AdminPage;
