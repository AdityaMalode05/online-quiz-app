package com.example.quizapp.dto;

import java.util.List;

public class QuizRequestDTO {
    private String title;
    private List<QuestionDTO> questions;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<QuestionDTO> getQuestions() { return questions; }
    public void setQuestions(List<QuestionDTO> questions) { this.questions = questions; }
}
