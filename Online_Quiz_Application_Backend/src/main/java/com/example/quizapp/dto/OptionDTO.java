package com.example.quizapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OptionDTO {
    private Long id;
    private String text;

    @JsonProperty("isCorrect") // ✅ ensures JSON <-> Java consistency
    private boolean isCorrect;

    public OptionDTO() {}

    public OptionDTO(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public OptionDTO(Long id, String text, boolean isCorrect) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    @JsonProperty("isCorrect") // ✅ makes sure JSON output contains `isCorrect`
    public boolean isCorrect() { return isCorrect; }

    public void setCorrect(boolean correct) { this.isCorrect = correct; }
}
