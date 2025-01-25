import tokenVerification from "../tokenVerification/tokenVerification";
import React, { createContext, useContext, useState, useEffect } from 'react';

const UserContext = createContext();
export const UserProvider = ({ children }) => {
    const [currentUser, setCurrentUser] = useState(null);


    const setUser = (user) => {
        setCurrentUser(user);
    };

    const logout = () => {
        localStorage.removeItem('jwtToken');
        setCurrentUser(null);

    };

    const verifyToken = async () => {
        const token = localStorage.getItem('jwtToken');
        if (token) {
            const userData = await tokenVerification(token);
            if (userData) {
                // alert(userData.phone);
                setCurrentUser(userData);
                return true;
            } else {
                alert("hey");
                // logout();
                return false;
            }
        }
    };
    return (
        <UserContext.Provider value={{
            currentUser,
            setUser,
            logout,
            verifyToken
        }}>
            {children}
        </UserContext.Provider>
    );
};
export const useUser = () => useContext(UserContext);