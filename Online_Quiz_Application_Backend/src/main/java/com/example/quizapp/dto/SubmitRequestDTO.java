package com.example.quizapp.dto;

import java.util.List;

public class SubmitRequestDTO {

    // ✅ Added to track which user submitted the quiz
    private Long userId;

    private List<Answer> answers;

    // ✅ Nested class for answers
    public static class Answer {
        private Long questionId;
        private Long selectedOptionId;

        public Long getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Long questionId) {
            this.questionId = questionId;
        }

        public Long getSelectedOptionId() {
            return selectedOptionId;
        }

        public void setSelectedOptionId(Long selectedOptionId) {
            this.selectedOptionId = selectedOptionId;
        }
    }

    // ✅ Getters and Setters for userId and answers
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
