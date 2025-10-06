package com.example.quizapp.repo;

import com.example.quizapp.models.Option;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {

	 List<Option> findByQuestionId(Long questionId);
	
}
