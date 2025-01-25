import "./MainPage.css";
import VideoItem from "../Components/VideoItem/VideoItem";
import RowSlider from "../Components/RowSlider/RowSlider";
import NumericSlider from "../Components/NumericSlider/NumericSlider";
import TopMenu from "../Components/TopMenu/TopMenu";
import { useUser } from "../Contexts/UserContext";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const comedyMovies = [
  { src: "/media/Animals-trending/dog.mp4", 
    title: "Pupies Life", 
    description: "A heartwarming story about puppies.",
    releaseYear: 2021,
    rating: 4.5,
    duration: "1h 30m", 
    categories: ["Family", "Adventure"], 
    cast: ["John Doe", "Jane Smith"], 
    additionalMovies: []  },
    { src: "/media/Animals-trending/dog.mp4", 
      title: "Pupies Life", 
      description: "A heartwarming story about puppies.",
      releaseYear: 2021,
      rating: 4.5,
      duration: "1h 30m", 
      categories: ["Family", "Adventure"], 
      cast: ["John Doe", "Jane Smith"], 
      additionalMovies: []  },
      { src: "/media/Animals-trending/dog.mp4", 
        title: "Pupies Life", 
        description: "A heartwarming story about puppies.",
        releaseYear: 2021,
        rating: 4.5,
        duration: "1h 30m", 
        categories: ["Family", "Adventure"], 
        cast: ["John Doe", "Jane Smith"], 
        additionalMovies: []  }
];

const trendingMovies = [
  { src: "/media/Animals-trending/dog.mp4", 
    title: "Pupies Life", 
    description: "A heartwarming story about puppies.",
    releaseYear: 2021,
    rating: 4.5,
    duration: "1h 30m", 
    categories: ["Family", "Adventure"], 
    cast: ["John Doe", "Jane Smith"], 
    additionalMovies: []  },
    { src: "/media/Animals-trending/dog.mp4", 
      title: "Pupies Life", 
      description: "A heartwarming story about puppies.",
      releaseYear: 2021,
      rating: 4.5,
      duration: "1h 30m", 
      categories: ["Family", "Adventure"], 
      cast: ["John Doe", "Jane Smith"], 
      additionalMovies: []  },
      { src: "/media/Animals-trending/dog.mp4", 
        title: "Pupies Life", 
        description: "A heartwarming story about puppies.",
        releaseYear: 2021,
        rating: 4.5,
        duration: "1h 30m", 
        categories: ["Family", "Adventure"], 
        cast: ["John Doe", "Jane Smith"], 
        additionalMovies: []  }
];
console.log("Movie data:", comedyMovies[0]);
comedyMovies.forEach(movie => movie.additionalMovies = trendingMovies);

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
        <NumericSlider title="Trending Now" movies={trendingMovies} />
        <RowSlider title="Comedy" movies={comedyMovies} />

      </div>
    </div>
  );
}

export default MainPage;
