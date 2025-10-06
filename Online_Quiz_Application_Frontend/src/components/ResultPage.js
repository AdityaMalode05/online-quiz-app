import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./ResultPage.css";

const ResultPage = () => {
  const { state } = useLocation();
  const navigate = useNavigate();

  if (!state) return <h2>No result data available!</h2>;

  const { score, total, questions } = state;

  return (
    <div className="result-container">
      <div className="result-card">
        <h1>🎯 Quiz Result</h1>
        <h2>
          Your Score: <span className="score">{score}</span> / {total}
        </h2>

        <div className="answers-section">
          {questions?.map((q) => (
            <div key={q.id} className="result-question">
              <h4>{q.text}</h4>
              {q.options.map((opt) => {
                const isSelected = q.selectedOptionId === opt.id;
                const isCorrect = opt.isCorrect;
                return (
                  <div
                    key={opt.id}
                    className={`result-option ${
                      isCorrect
                        ? "correct"
                        : isSelected && !isCorrect
                        ? "wrong"
                        : ""
                    }`}
                  >
                    {opt.text}
                    {isSelected && !isCorrect && (
                      <span className="status"> ❌ Your Choice</span>
                    )}
                    {isCorrect && (
                      <span className="status"> ✅ Correct Answer</span>
                    )}
                  </div>
                );
              })}
            </div>
          ))}
        </div>

        <button onClick={() => navigate("/")}>🏁 Take Another Quiz</button>
      </div>
    </div>
  );
};

export default ResultPage;
