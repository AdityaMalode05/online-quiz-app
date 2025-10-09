package com.example.quizapp.controller;

import com.example.quizapp.models.Result;
import com.example.quizapp.models.User;
import com.example.quizapp.repo.ResultRepository;
import com.example.quizapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "http://localhost:3000")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    // Save result after quiz submission
    @PostMapping
    public Result saveResult(@RequestBody Result result) {
        if (result.getUser() == null || result.getUser().getId() == null) {
            throw new RuntimeException("User information missing");
        }
        User user = userRepository.findById(result.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        result.setUser(user);
        result.setSubmittedAt(java.time.LocalDateTime.now());
        return resultRepository.save(result);
    }

    // Get all results for a user
    @GetMapping("/user/{userId}")
    public List<Result> getUserResults(@PathVariable Long userId) {
        return resultRepository.findByUserId(userId);
    }
}
