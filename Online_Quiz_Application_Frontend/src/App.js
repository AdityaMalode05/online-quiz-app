import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import StartPage from "./components/StartPage";
import QuizPage from "./components/QuizPage";
import ResultPage from "./components/ResultPage";
import ResultHistory from "./components/ResultHistory";
// import Header from "./components/Header";
import { PrivateRoute } from "./components/PrivateRoute";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />

        {/* Protected routes with Header */}
        <Route path="/" element={<PrivateRoute><><StartPage /></></PrivateRoute>} />
        <Route path="/quiz/:quizId" element={<PrivateRoute><><QuizPage /></></PrivateRoute>} />
        <Route path="/result" element={<PrivateRoute><><ResultPage /></></PrivateRoute>} />
        <Route path="/results" element={<PrivateRoute><><ResultHistory /></></PrivateRoute>} />
      </Routes>
    </Router>
  );
}

export default App;
