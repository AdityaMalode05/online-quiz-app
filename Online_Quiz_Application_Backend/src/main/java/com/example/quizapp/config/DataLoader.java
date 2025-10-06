package com.example.quizapp.config;

import com.example.quizapp.models.*;
import com.example.quizapp.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(QuizRepository quizRepo,
                               QuestionRepository questionRepo,
                               OptionRepository optionRepo) {
        return args -> {
            if (quizRepo.count() == 0) {
                Quiz quiz = new Quiz();
                quiz.setTitle("Java Basics Quiz");
                quizRepo.save(quiz);

                Question q1 = new Question(null, "What is the size of int in Java?", quiz, null);
                Question q2 = new Question(null, "Which keyword is used to inherit a class in Java?", quiz, null);
                Question q3 = new Question(null, "Which of the following is not a Java primitive type?", quiz, null);
                Question q4 = new Question(null, "What does JVM stands for?", quiz, null);
                questionRepo.saveAll(List.of(q1, q2, q3, q4));

                optionRepo.saveAll(List.of(
                    new Option(null, "2 bytes", false, q1),
                    new Option(null, "4 bytes", true, q1),
                    new Option(null, "8 bytes", false, q1),
                    new Option(null, "16 bytes", false, q1),

                    new Option(null, "import", false, q2),
                    new Option(null, "this", false, q2),
                    new Option(null, "extends", true, q2),
                    new Option(null, "implements", false, q2),

                    new Option(null, "int", false, q3),
                    new Option(null, "boolean", false, q3),
                    new Option(null, "float", false, q3),
                    new Option(null, "String", true, q3),
                    
                    new Option(null, "Java Virtual Machine", true, q4),
                    new Option(null, "Java Verification Module", false, q4),
                    new Option(null, "Java Verification Module", false, q4),
                    new Option(null, "Java Virtual Memory", false, q4)
                ));

                System.out.println("✅ Sample quiz data inserted!");
            }
        };
    }
}