import React from "react";
import "./SignUpMessage.css";
import signuplogo from "../../images/signuplogo.png";
import { Link } from "react-router-dom";
import NetflixLogo from "../../Components/NetflixLogo/NetflixLogo";



const SignUpMessage = () => {
  return (
    <div className="signup-container">
     <NetflixLogo/>
      <div className="signup-step">STEP 1 OF 2</div>
      <div className="signup-title">Finish setting up your account</div>
      <div className="signup-description">
        Netflix is personalized for you. Let's fill in your details
      </div>
      <div className="signup-image-container">
        <img src={signuplogo} alt="Signup Logo" className="signup-image" />
      </div>
      <Link to="/FillDetails">
      <button className="signup-button">Next</button>
      </Link>
    </div>
  );
};

export default SignUpMessage;
