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
        Optional<Quiz> quizOpt = quizRepository.findById(quizId);
        if (quizOpt.isEmpty()) {
            return Map.of("score", 0, "total", 0, "questions", Collections.emptyList());
        }

        Quiz quiz = quizOpt.get();
        List<Question> questions = Optional.ofNullable(quiz.getQuestions()).orElse(Collections.emptyList());
        int total = questions.size();

        // Build optionId -> Option map
        Map<Long, Option> optionMap = new HashMap<>();
        for (Question q : questions) {
            if (q.getOptions() != null) {
                for (Option o : q.getOptions()) {
                    optionMap.put(o.getId(), o);
                }
            }
        }

        int score = 0;
        Map<Long, Long> selectedAnswers = new HashMap<>();
        if (submitRequest.getAnswers() != null) {
            for (SubmitRequestDTO.Answer a : submitRequest.getAnswers()) {
                selectedAnswers.put(a.getQuestionId(), a.getSelectedOptionId());
                Option selected = optionMap.get(a.getSelectedOptionId());
                if (selected != null && selected.isCorrect()) score++;
            }
        }

        // Prepare detailed question result
        List<Map<String, Object>> questionDetails = new ArrayList<>();
        for (Question q : questions) {
            Map<String, Object> qMap = new LinkedHashMap<>();
            qMap.put("id", q.getId());
            qMap.put("text", q.getText());
            qMap.put("selectedOptionId", selectedAnswers.get(q.getId()));

            List<Map<String, Object>> optionList = new ArrayList<>();
            for (Option o : q.getOptions()) {
                Map<String, Object> oMap = new LinkedHashMap<>();
                oMap.put("id", o.getId());
                oMap.put("text", o.getText());
                oMap.put("isCorrect", o.isCorrect());
                optionList.add(oMap);
            }
            qMap.put("options", optionList);
            questionDetails.add(qMap);
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("score", score);
        result.put("total", total);
        result.put("questions", questionDetails);
        return result;
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



}
