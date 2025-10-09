import React, { useEffect, useState } from "react";
import { getUserResults } from "../services/resultService";
import "./ResultHistory.css";
import Header from "./Header";

const ResultHistory = () => {
  const [results, setResults] = useState([]);
  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    if (user) {
      getUserResults(user.id).then(setResults).catch(console.error);
    }
  }, [user]);

  return (
    <>
      <Header />
      <div className="history-container">
        <div className="history-card">
          <h1 className="history-title">📊 Your Quiz Results</h1>

          {results.length > 0 ? (
            <div className="table-wrapper">
              <table className="history-table">
                <thead>
                  <tr>
                    <th>Quiz Title</th>
                    <th>Score</th>
                    <th>Total</th>
                    <th>Date</th>
                  </tr>
                </thead>
                <tbody>
                  {results.map((r) => (
                    <tr key={r.id}>
                      <td>{r.quizTitle}</td>
                      <td>{r.score}</td>
                      <td>{r.totalQuestions}</td>
                      <td>{new Date(r.submittedAt).toLocaleString()}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          ) : (
            <p className="no-results">No quiz results found yet.</p>
          )}
        </div>
      </div>
    </>
  );
};

export default ResultHistory;
