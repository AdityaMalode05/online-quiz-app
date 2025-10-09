import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./StartPage.css";
import Header from "./Header";

const StartPage = () => {
  const [quizzes, setQuizzes] = useState([]);
  const [selectedQuizId, setSelectedQuizId] = useState("");
  const [username, setUsername] = useState("");
  const navigate = useNavigate();

  // Fetch all quizzes on component mount
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/quizzes")
      .then((res) => {
        setQuizzes(res.data);
      })
      .catch((err) => {
        console.error("Error fetching quizzes:", err);
      });

    // Get logged-in user's name from localStorage
    const user = JSON.parse(localStorage.getItem("user"));
    if (user?.username) {
      setUsername(user.username);
    }
  }, []);

  const handleStart = () => {
    if (selectedQuizId) {
      navigate(`/quiz/${selectedQuizId}`);
    } else {
      alert("Please select a quiz to start!");
    }
  };

  return (
    <>
      <Header />
      <div className="start-container">
        {username && (
          <h2 className="greeting">👋 Hello, {username}!</h2>
        )}
        <div className="start-card">
          <h1 className="start-title">🧠 Online Quiz App</h1>
          <p className="start-subtitle">Select a quiz to start</p>
          <div className="input-group">
            <select
              value={selectedQuizId}
              onChange={(e) => setSelectedQuizId(e.target.value)}
            >
              <option value="">-- Select Quiz --</option>
              {quizzes.map((quiz) => (
                <option key={quiz.id} value={quiz.id}>
                  {quiz.title}
                </option>
              ))}
            </select>
            <button className="btn" onClick={handleStart}>Start Quiz</button>
          </div>
        </div>
      </div>
    </>
  );
};

export default StartPage;
