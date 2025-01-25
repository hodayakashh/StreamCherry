import "./MainPage.css";
import VideoItem from "../Components/VideoItem/VideoItem";
import RowSlider from "../Components/RowSlider/RowSlider";
import TopMenu from "../Components/TopMenu/TopMenu";
import { useUser } from "../Contexts/UserContext";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function MainPage() {
  const { logout, verifyToken } = useUser();
  const navigate = useNavigate();

  const LogOut = () => {
    logout();
    navigate("/");
  };

  // Perform token verification on component mount
  useEffect(() => {
    const checkToken = async () => {
      const isValid = await verifyToken(); // Assume verifyToken returns a boolean
      if (!isValid) {
        logout();
        navigate("/login"); // Redirect to login page if token is invalid
      }
    };

    checkToken();
  }, [verifyToken, logout, navigate]); // Dependencies for useEffect

  return (
    <div className="MainPage">
      {/* Video Background Section */}
      <div className="VideoBackground">
        <VideoItem />
      </div>
      <div className="overlay">
        <TopMenu LogOutSystem={LogOut} />
      </div>
      {/* Main Content Section */}
      <div className="MainContent">
        <RowSlider title="Trending Now" />
        <RowSlider title="Comedy" />
        <RowSlider title="Action" />
      </div>
    </div>
  );
}

export default MainPage;
