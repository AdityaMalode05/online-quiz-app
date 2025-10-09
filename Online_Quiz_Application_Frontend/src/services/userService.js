import axios from "axios";
const API_URL = "http://localhost:8080/api/users";

export const loginUser = async (user) => {
  const res = await axios.post(`${API_URL}/login`, user);
  return res.data;
};

export const registerUser = async (user) => {
  const res = await axios.post(`${API_URL}/register`, user);
  return res.data;
};
