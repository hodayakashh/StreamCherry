import React from "react";
import { Link } from "react-router-dom";
import NetflixLogo from "../NetflixLogo/NetflixLogo";
const FormHeader = () => {
  return (
    <>
      <Link to="/">
        <NetflixLogo />
      </Link>
      <Link to="/login">
        <button type="button" className="btn btn-signin">
          Sign in
        </button>
      </Link>
      <div className="line"></div>
    </>
  );
};

export default FormHeader;
