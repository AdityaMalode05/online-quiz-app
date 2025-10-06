package com.example.quizapp.models;


import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // parent side (of Option)
    private List<Option> options;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonBackReference // child side
    private Quiz quiz;
    

    // constructors, getters, setters
    public Question() {}
    public Question(String text) { this.text = text; }
    
    public Question(Long id, String text, Quiz quiz, List<Option> options) {
        this.id = id;
        this.text = text;
        this.quiz = quiz;
        this.options = options;
        }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<Option> getOptions() { return options; }
    public void setOptions(List<Option> options) { this.options = options; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }
}
