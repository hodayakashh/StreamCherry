import MainPage from "./MainPage/MainPage";
import Login from "./Login/Login";
import AdminPage from "./AdminPage/AdminPage";
import SignUpMessage from "./SignUp/SignUpMessage/SignUpMessage";
import HomePage from "./HomePage/HomePage";
import SignUpForm from "./SignUp/signUpForm/signUpForm";
import MoviePage from "./MoviePage/MoviePage";
import { UserProvider } from "./Contexts/UserContext";
import  SearchPage  from "./SearchPage/SearchPage";
import "./App.css";
import './styles/styles.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <UserProvider>
    <BrowserRouter> 
         <div className="App">
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/main" element={<MainPage />} />
        <Route path="/home" element={<MainPage />} />
        <Route path="/SignUp" element ={<SignUpMessage/>}/>
        <Route path="/FillDetails" element={<SignUpForm/>}/>
        <Route path="/movie" element={<MoviePage/>}/>
        <Route path="/FillDetails" element={<SignUpForm/>} />
        <Route path="/search/:query?" element={<SearchPage />} />
        <Route path="/admin-zone" element={<AdminPage />} />
      </Routes>
    </div>
    </BrowserRouter>
    </UserProvider>
  );
}

export default App;
