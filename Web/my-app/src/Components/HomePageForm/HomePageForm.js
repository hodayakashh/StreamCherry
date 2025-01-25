import {  Link } from 'react-router-dom';
import NetflixLogo from '../NetflixLogo/NetflixLogo';

function HomePageForm() {
    return (
        <div>
            <div className="homepage">
            <div className="overlay">
                <NetflixLogo/>
                <div className="container">
                    <h1>Unlimited movies, TV shows, and more</h1>
                    <p>Starts at ₪1,000,000 Cancel anytime.</p>
                    <p>Ready to watch? Enter your email to create or restart your membership.</p>
                    <div className="form">
                        <input type="email" placeholder="Email address" className="email-input" />
                        <Link to="/SignUp">
                        <button className="button">Get Started</button>
                        </Link>
                    </div>
                </div>
            </div>
            <div className="dropdown">
                <a className="btn english-button dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    English
                </a>

                <ul className="dropdown-menu">
                    <li><a className="dropdown-item" href="#">Hebrew</a></li>
                    <li><a className="dropdown-item" href="#">Uk</a></li>
                    <li><a className="dropdown-item" href="#">Binary</a></li>
                </ul>
            </div>
            <div>
                <Link to="/login">
                    <button type="button" className="btn btn-signin">Sign in</button>
                </Link>
            </div>

        </div>
        </div>

    )
}
export default HomePageForm;