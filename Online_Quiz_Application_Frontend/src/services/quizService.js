import axios from "axios";
import { API_BASE_URL } from "../config";

export const fetchQuizQuestions = async (quizId) => {
  const response = await axios.get(`${API_BASE_URL}/quizzes/${quizId}/questions`);
  return response.data;
};

export const submitQuizAnswers = async (quizId, answers) => {
  const response = await axios.post(`${API_BASE_URL}/quizzes/${quizId}/submit`, {
    answers: answers,
  });
  return response.data;
};
