import React from "react";
import "./QuestionCard.css";

const QuestionCard = ({ question, selectedOption, onSelectOption }) => {
  return (
    <div className="question-card">
      <h3 className="question-text">{question.text}</h3>
      <div className="options">
        {question.options.map((opt) => (
          <div
            key={opt.id}
            className={`option ${selectedOption === opt.id ? "selected" : ""}`}
            onClick={() => onSelectOption(question.id, opt.id)}
            role="button"
            tabIndex={0}
            onKeyPress={(e) => {
              if (e.key === "Enter" || e.key === " ") onSelectOption(question.id, opt.id);
            }}
          >
            <input
              type="radio"
              name={`question-${question.id}`}
              checked={selectedOption === opt.id}
              onChange={() => onSelectOption(question.id, opt.id)}
              className="option-radio"
            />
            <span className="option-text">{opt.text}</span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default QuestionCard;
