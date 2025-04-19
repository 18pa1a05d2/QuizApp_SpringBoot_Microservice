package com.host.quizapp.repo;

import com.host.quizapp.pojo.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
