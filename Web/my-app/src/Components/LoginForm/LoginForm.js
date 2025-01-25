import React from "react";
import "./LoginForm.css";
import { Link } from "react-router-dom";

function LoginForm({ onSignIn, onChange, username, password }) {
  return (
    <div className="login-overlay">
      <div className="login-container">
        <div className="login-form">
          <h1 className="text-white mb-4">Sign In</h1>
          {/* Email Input */}
          <div className="form-floating mb-3">
            <input
              type="email"
              className="form-control"
              id="floatingInput"
              placeholder="Username"
              name="username"
              value={username}
              onChange={onChange}
            />
            <label htmlFor="floatingInput">Username</label>
          </div>
          {/* Password Input */}
          <div className="form-floating mb-3">
            <input
              type="password"
              className="form-control"
              id="floatingPassword"
              placeholder="Password"
              name="password"
              value={password}
              onChange={onChange}
            />
            <label htmlFor="floatingPassword">Password</label>
          </div>
          {/* Buttons */}
          <div className="button-container">
            <button
              type="submit"
              className="btn btn-danger w-100 mb-3"
              onClick={onSignIn}
            >
              Sign In
            </button>
          </div>
          {/* Sign Up Section */}
          <div className="mt-4 new-to-netflix text-center">
            <span className="text-white">
              New to Netflix?{" "}
              <Link to="/SignUp">
                <a href="#" className="text-decoration-none text-danger">
                  Sign up now.
                </a>
              </Link>
            </span>
          </div>
          {/* Protection Notice */}
          <div className="text-center mt-4 small text-white-50">
            This page is protected by Hodaya Sahar and Shahak to ensure you're
            not a bot.{" "}
          </div>
        </div>
      </div>
    </div>
  );
}

export default LoginForm;