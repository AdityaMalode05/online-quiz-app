package com.example.quizapp.service;

import com.example.quizapp.dto.OptionDTO;
import com.example.quizapp.dto.QuestionDTO;
import com.example.quizapp.dto.QuizRequestDTO;
import com.example.quizapp.dto.SubmitRequestDTO;
import com.example.quizapp.models.Option;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.Quiz;
import com.example.quizapp.repo.OptionRepository;
import com.example.quizapp.repo.QuestionRepository;
import com.example.quizapp.repo.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    public QuizService(QuizRepository quizRepository,
                       QuestionRepository questionRepository,
                       OptionRepository optionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public List<QuestionDTO> getQuestionsForQuiz(Long quizId) {
        Optional<Quiz> qOpt = quizRepository.findById(quizId);
        if (qOpt.isEmpty()) return Collections.emptyList();

        List<Question> questions = qOpt.get().getQuestions();
        if (questions == null) return Collections.emptyList();

        return questions.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private QuestionDTO toDTO(Question question) {
        List<OptionDTO> options = Optional.ofNullable(question.getOptions())
                .orElse(Collections.emptyList())
                .stream()
                .map(o -> new OptionDTO(o.getId(), o.getText()))
                .collect(Collectors.toList());
        return new QuestionDTO(question.getId(), question.getText(), options);
    }



    public Map<String, Object> submitAnswers(Long quizId, SubmitRequestDTO submitRequest) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        List<Question> questions = quiz.getQuestions();

        int score = 0;
        List<Map<String, Object>> questionResults = new ArrayList<>();

        for (Question q : questions) {
            Long selectedOptionId = submitRequest.getAnswers().stream()
                    .filter(a -> a.getQuestionId().equals(q.getId()))
                    .map(a -> a.getSelectedOptionId())
                    .findFirst()
                    .orElse(null);

            boolean correct = q.getOptions().stream()
                    .anyMatch(opt -> opt.getId().equals(selectedOptionId) && opt.isCorrect());

            if (correct) score++;

            Map<String, Object> qMap = new HashMap<>();
            qMap.put("id", q.getId());
            qMap.put("text", q.getText());
            qMap.put("options", q.getOptions());
            qMap.put("selectedOptionId", selectedOptionId);

            questionResults.add(qMap);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("score", score);
        resultMap.put("total", questions.size());
        resultMap.put("questions", questionResults);


        return resultMap;
    }

    

 // // Create New Quiz with Questions 
    public Quiz createQuiz(QuizRequestDTO quizRequest) {
        // Save quiz entity
        Quiz quiz = new Quiz();
        quiz.setTitle(quizRequest.getTitle());
        quizRepository.save(quiz);

        List<Question> savedQuestions = new ArrayList<>();

        for (QuestionDTO qdto : quizRequest.getQuestions()) {
            Question question = new Question();
            question.setText(qdto.getText());
            question.setQuiz(quiz);
            questionRepository.save(question);

            List<Option> savedOptions = new ArrayList<>();
            for (OptionDTO odto : qdto.getOptions()) {
                Option option = new Option();
                option.setText(odto.getText());
                option.setCorrect(odto.isCorrect()); 
                option.setQuestion(question);
                optionRepository.save(option);
                savedOptions.add(option);
            }

            question.setOptions(savedOptions);
            savedQuestions.add(question);
        }

        quiz.setQuestions(savedQuestions); 

        return quiz; 
    }


    //  Add Question to Existing Quiz
    public Question addQuestionToQuiz(Long quizId, QuestionDTO questionDTO) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        Question question = new Question();
        question.setText(questionDTO.getText());
        question.setQuiz(quiz);
        questionRepository.save(question);

        for (OptionDTO optDTO : questionDTO.getOptions()) {
            Option option = new Option();
            option.setText(optDTO.getText());
            option.setCorrect(optDTO.isCorrect());
            option.setQuestion(question);
            optionRepository.save(option);
        }

        // Refresh options
        question.setOptions(optionRepository.findByQuestionId(question.getId()));

        return question;
    }
    
    // for deleting a quiz
    public void deleteQuiz(Long quizId) {
        // Fetch the quiz
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        // Delete all questions and their options
        List<Question> questions = Optional.ofNullable(quiz.getQuestions()).orElse(Collections.emptyList());
        for (Question q : questions) {
            if (q.getOptions() != null && !q.getOptions().isEmpty()) {
                optionRepository.deleteAll(q.getOptions()); // Delete options
            }
            questionRepository.delete(q); // Delete question
        }

        // Finally, delete the quiz
        quizRepository.delete(quiz);
    }


 // // for deleting a question
    public void deleteQuestion(Long questionId) {
        // Fetch the question
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // Delete all options for this question
        if (question.getOptions() != null && !question.getOptions().isEmpty()) {
            optionRepository.deleteAll(question.getOptions());
        }

        // Delete the question
        questionRepository.delete(question);
    }

    public Quiz getQuizById(Long quizId) {
        return quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
    }


}
