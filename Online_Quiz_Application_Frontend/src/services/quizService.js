import axios from "axios";
import { API_BASE_URL } from "../config";

export const fetchQuizQuestions = async (quizId) => {
  const response = await axios.get(`${API_BASE_URL}/quizzes/${quizId}/questions`);
  return response.data;
};

export const submitQuizAnswers = async (quizId, answers) => {
  const user = JSON.parse(localStorage.getItem("user"));
  if (!user) throw new Error("User not logged in");

  const payload = { userId: user.id, answers };
  const res = await axios.post(`http://localhost:8080/api/quizzes/${quizId}/submit`, payload);
  return res.data;  // will now include score, total, questions with selectedOptionId
};

