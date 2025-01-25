import React, { useState } from "react";
import "./signUpForm.css";
import { Link, useNavigate } from "react-router-dom";
import { useUser } from "../../Contexts/UserContext";
import FormHeader from "../../Components/SignUpComponents/FormHeader";
import FileInput from "../../Components/SignUpComponents/FileInput";
import FormInput from "../../Components/SignUpComponents/FormInput";
import DropdownInput from "../../Components/SignUpComponents/DropdownInput";
import ThemeToggle from "../../Components/ThemeToggle/ThemeToggle";


const SignUpForm = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    email: "",
    phone: "",
    profilePicture: null,
    manager: false, // Default value is false
  });
  const { setUser } = useUser();
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: name === "manager" ? value === "yes" : value,
    });
  };

  const handleFileChange = (e) => {
    setFormData({
      ...formData,
      profilePicture: e.target.files[0],
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const userJson = {
      user_name: formData.username,
      password: formData.password,
      mail: formData.email,
      phone: formData.phone,
      picture: formData.profilePicture,
      manager: formData.manager,
    };

    try {
      const response = await fetch("http://localhost:3000/api/users", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userJson),
      });

      const data = await response.json();

      if (response.ok) {
        const token = data.token;
        localStorage.setItem("jwtToken", token);
        setUser(data.user);
        navigate("/main");
        alert("User Successfully Registered");
      } else {
        alert("Registry error: " + (data.error || "Unknown error"));
      }
    } catch (error) {
      alert("Server Error: " + error.message);
    }
  };

  return (
    <div className="signup-page">
      <FormHeader />
      <ThemeToggle />
      <div className="signup-form-container">
        <h2>Create an Account</h2>
        <form onSubmit={handleSubmit}>
          <FormInput
            label="Username"
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
          />
          <FormInput
            label="Password"
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
          />
          <FormInput
            label="Email"
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
          />
          <FormInput
            label="Phone Number"
            type="text"
            name="phone"
            value={formData.phone}
            onChange={handleChange}
          />
          <FileInput label="Profile Picture" onChange={handleFileChange} />
          <DropdownInput
            label="Are you a Manager?"
            name="manager"
            value={formData.manager ? "yes" : "no"}
            options={[
              { value: "", label: "Select an option" },
              { value: "yes", label: "Yes" },
              { value: "no", label: "No" },
            ]}
            onChange={handleChange}
          />
          <button type="submit" className="submit-btn">
            Sign Up
          </button>
        </form>
      </div>
    </div>
  );
};
export default SignUpForm;
