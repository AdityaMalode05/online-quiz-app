package com.example.quizapp.controller;

import com.example.quizapp.dto.QuestionDTO;
import com.example.quizapp.dto.QuizRequestDTO;
import com.example.quizapp.dto.SubmitRequestDTO;
import com.example.quizapp.models.Option;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.Quiz;
import com.example.quizapp.repo.QuestionRepository;
import com.example.quizapp.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "*")
public class QuizController {

    private final QuizService service;
    
    public QuizController(QuizService service) {
		this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(service.getAllQuizzes());
    }

    @GetMapping("/{quizId}/questions")
    public ResponseEntity<List<QuestionDTO>> getQuestions(@PathVariable Long quizId) {
        return ResponseEntity.ok(service.getQuestionsForQuiz(quizId));
    }

    @PostMapping("/{quizId}/submit")
    public ResponseEntity<Map<String, Object>> submitAnswers(@PathVariable Long quizId,
                                                             @RequestBody SubmitRequestDTO submitRequest) {
        Map<String, Object> result = service.submitAnswers(quizId, submitRequest);
        return ResponseEntity.ok(result);
    }

    
    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizRequestDTO quizRequest) {
        return ResponseEntity.ok(service.createQuiz(quizRequest));
    }

    @PostMapping("/{quizId}/add-questions")
    public ResponseEntity<List<Question>> addQuestions(@PathVariable Long quizId, 
                                                       @RequestBody List<QuestionDTO> questionsDTO) {
        List<Question> addedQuestions = questionsDTO.stream()
            .map(dto -> service.addQuestionToQuiz(quizId, dto))
            .collect(Collectors.toList());
        return ResponseEntity.ok(addedQuestions);
    }

    
    @DeleteMapping("/{quizId}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long quizId) {
        service.deleteQuiz(quizId);
        return ResponseEntity.ok("Quiz deleted successfully");
    }
    
    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {
        service.deleteQuestion(questionId);
        return ResponseEntity.ok("Question deleted successfully");
    }


}
