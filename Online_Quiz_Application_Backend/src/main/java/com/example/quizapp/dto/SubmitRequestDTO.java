package com.example.quizapp.dto;

import java.util.List;

public class SubmitRequestDTO {
    public static class Answer {
        private Long questionId;
        private Long selectedOptionId;

        public Long getQuestionId() { return questionId; }
        public void setQuestionId(Long questionId) { this.questionId = questionId; }

        public Long getSelectedOptionId() { return selectedOptionId; }
        public void setSelectedOptionId(Long selectedOptionId) { this.selectedOptionId = selectedOptionId; }
    }

    private List<Answer> answers;

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }
}
