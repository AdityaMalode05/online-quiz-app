import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { fetchQuizQuestions, submitQuizAnswers } from "../services/quizService";
import QuestionCard from "./QuestionCard";
import { QUIZ_TIME_PER_QUESTION } from "../config";
import "./QuizPage.css";

const QuizPage = () => {
  const { quizId } = useParams();
  const [questions, setQuestions] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [answers, setAnswers] = useState({});
  const [timeLeft, setTimeLeft] = useState(0);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  //  Fetch quiz questions
  useEffect(() => {
    const loadQuestions = async () => {
      try {
        const data = await fetchQuizQuestions(quizId);
        setQuestions(data);
      } catch (error) {
        console.error("Error fetching questions:", error);
      } finally {
        setLoading(false);
      }
    };
    loadQuestions();
  }, [quizId]);

  //  Initialize total time (1 min per question)
  useEffect(() => {
    if (questions.length > 0) {
      setTimeLeft(questions.length * QUIZ_TIME_PER_QUESTION);
    }
  }, [questions]);

  // Countdown timer logic
  useEffect(() => {
    if (timeLeft <= 0) return;
    const timer = setInterval(() => {
      setTimeLeft((prev) => {
        if (prev <= 1) {
          clearInterval(timer);
          handleSubmit(); // Auto submit when time runs out
          return 0;
        }
        return prev - 1;
      });
    }, 1000);

    return () => clearInterval(timer);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [timeLeft]);

  //  Handle selecting answers
  const handleAnswerSelect = (questionId, optionId) => {
    setAnswers({ ...answers, [questionId]: optionId });
  };

  // ⏭ Next question
  const handleNext = () => {
    if (currentIndex < questions.length - 1) {
      setCurrentIndex(currentIndex + 1);
    }
  };

  //  Previous question
  const handlePrev = () => {
    if (currentIndex > 0) {
      setCurrentIndex(currentIndex - 1);
    }
  };

  //  Submit quiz answers
    const handleSubmit = async () => {
  const answerArray = Object.entries(answers).map(([questionId, optionId]) => ({
    questionId: Number(questionId),
    selectedOptionId: optionId,
  }));

  try {
    const result = await submitQuizAnswers(quizId, answerArray);
    navigate("/result", { state: result }); // no need to wrap in {result}
  } catch (err) {
    alert(err.response?.data || "Error submitting quiz");
  }
};


  //  Display loading or missing quiz messages
  if (loading) return <h2>Loading questions...</h2>;
  if (!questions.length) return <h2>No questions found for this quiz!</h2>;

  const question = questions[currentIndex];

  //  Format timer (MM:SS)
  const formatTime = (seconds) => {
    const m = Math.floor(seconds / 60)
      .toString()
      .padStart(2, "0");
    const s = (seconds % 60).toString().padStart(2, "0");
    return `${m}:${s}`;
  };

  return (
  <div className="quiz-container-wrapper">
    <div className="quiz-container">
      <div className="quiz-header">
        <div className="progress-bar">
          <div
            className="progress-fill"
            style={{
              width: `${((currentIndex + 1) / questions.length) * 100}%`,
            }}
          ></div>
        </div>
        <div className={`timer ${timeLeft <= 30 ? "warning" : ""}`}>
          ⏱ {formatTime(timeLeft)}
        </div>
      </div>

      <QuestionCard
        question={question}
        selectedOption={answers[question.id]}
        onSelectOption={handleAnswerSelect}
      />

      <div className="navigation-buttons">
        <button onClick={handlePrev} disabled={currentIndex === 0}>
          Previous
        </button>
        {currentIndex < questions.length - 1 ? (
          <button onClick={handleNext}>Next</button>
        ) : (
          <button onClick={handleSubmit}>Submit</button>
        )}
      </div>
    </div>
  </div>
);
};

export default QuizPage;
