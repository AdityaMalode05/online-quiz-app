package com.example.quizapp.controller;

import com.example.quizapp.dto.QuestionDTO;
import com.example.quizapp.dto.QuizRequestDTO;
import com.example.quizapp.dto.SubmitRequestDTO;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.Quiz;
import com.example.quizapp.models.Result;
import com.example.quizapp.models.User;
import com.example.quizapp.repo.ResultRepository;
import com.example.quizapp.repo.UserRepository;
import com.example.quizapp.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "*")
public class QuizController {

    private final QuizService service;
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;

    public QuizController(QuizService service, ResultRepository resultRepository, UserRepository userRepository) {
        this.service = service;
        this.resultRepository = resultRepository;
        this.userRepository = userRepository;
    }

    // ✅ Get all quizzes
    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(service.getAllQuizzes());
    }

    // ✅ Get questions for a quiz
    @GetMapping("/{quizId}/questions")
    public ResponseEntity<List<QuestionDTO>> getQuestions(@PathVariable Long quizId) {
        return ResponseEntity.ok(service.getQuestionsForQuiz(quizId));
    }

    // ✅ Submit quiz answers and also store result for the user
    @PostMapping("/{quizId}/submit")
    public ResponseEntity<Map<String, Object>> submitAnswers(@PathVariable Long quizId,
                                                             @RequestBody SubmitRequestDTO submitRequest) {
        // Step 1: Calculate score using QuizService
        Map<String, Object> resultMap = service.submitAnswers(quizId, submitRequest);

        // Step 2: Extract score and total
        int score = (int) resultMap.get("score");
        int total = (int) resultMap.get("total");

        // Step 3: Get user from request
        Long userId = submitRequest.getUserId();
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Step 4: Fetch quiz title
            Quiz quiz = service.getQuizById(quizId);

            // Step 5: Save result
            Result result = new Result();
            result.setQuizTitle(quiz.getTitle());
            result.setScore(score);
            result.setTotalQuestions(total);
            result.setSubmittedAt(LocalDateTime.now());
            result.setUser(user);

            resultRepository.save(result);
        }

        return ResponseEntity.ok(resultMap);
    }

    // ✅ Create a new quiz
    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizRequestDTO quizRequest) {
        return ResponseEntity.ok(service.createQuiz(quizRequest));
    }

    // ✅ Add questions to a quiz
    @PostMapping("/{quizId}/add-questions")
    public ResponseEntity<List<Question>> addQuestions(@PathVariable Long quizId,
                                                       @RequestBody List<QuestionDTO> questionsDTO) {
        List<Question> addedQuestions = questionsDTO.stream()
                .map(dto -> service.addQuestionToQuiz(quizId, dto))
                .collect(Collectors.toList());
        return ResponseEntity.ok(addedQuestions);
    }

    // ✅ Delete a quiz
    @DeleteMapping("/{quizId}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long quizId) {
        service.deleteQuiz(quizId);
        return ResponseEntity.ok("Quiz deleted successfully");
    }

    // ✅ Delete a question
    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {
        service.deleteQuestion(questionId);
        return ResponseEntity.ok("Question deleted successfully");
    }
}
