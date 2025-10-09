import axios from "axios";
const API_URL = "http://localhost:8080/api/results";

export const getUserResults = async (userId) => {
  const res = await axios.get(`${API_URL}/user/${userId}`);
  return res.data;
};
