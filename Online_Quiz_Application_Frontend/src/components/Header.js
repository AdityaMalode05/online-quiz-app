import React from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { FaSignOutAlt } from "react-icons/fa"; // Logout icon
import "./Header.css";
import logo from "../assets/logo.png"; 
const Header = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/login");
  };

  return (
    <header className="navbar">
      <div className="nav-left">
        <img
          src={logo}
          alt="Quiz Logo"
          className="nav-logo"
          onClick={() => navigate("/")}
        />
      </div>

      <div className="nav-center">
        <span
          className={`nav-link ${location.pathname === "/" ? "active" : ""}`}
          onClick={() => navigate("/")}
        >
          Quizzes
        </span>
        <span
          className={`nav-link ${
            location.pathname === "/results" ? "active" : ""
          }`}
          onClick={() => navigate("/results")}
        >
          View Results
        </span>
      </div>

      <div className="nav-right">
        <button className="logout-btn" onClick={handleLogout}>
          <FaSignOutAlt className="logout-icon" />
          Logout
        </button>
      </div>
    </header>
  );
};

export default Header;
