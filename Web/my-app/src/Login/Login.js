import "./Login.css";
import React, { useState } from 'react';
import LoginForm from "../Components/LoginForm/LoginForm";
import NetflixLogo from "../Components/NetflixLogo/NetflixLogo";
import { Link, useNavigate } from "react-router-dom";
import { useUser } from "../Contexts/UserContext";

function Login() {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const navigate = useNavigate();
  const { setUser } = useUser();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const userJson = {
      user_name: formData.username,
      password: formData.password,
    };

    try {
      const response = await fetch("http://localhost:3000/api/tokens", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userJson),
      });

      const data = await response.json();

      if (response.ok) {
        setUser(data.user);
        localStorage.setItem('jwtToken', data.token);
        alert('Login successful');
        navigate('/main');
      } else {
        alert("Registry error: " + (data.error || "Unknown error"));
      }
    } catch (error) {
      alert("Server Error: " + error.message);
    }
  };

  return (
    <div className="Login">
      <div className="loginPage">
        <Link to="/">
          <NetflixLogo />
        </Link>
        <LoginForm 
          onSignIn={handleSubmit}
          onChange={handleChange}
          username={formData.username}
          password={formData.password}
        />
      </div>
    </div>
  );
}

export default Login;