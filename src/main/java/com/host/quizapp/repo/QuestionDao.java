package com.host.quizapp.repo;

import com.host.quizapp.pojo.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> getQuestionByCategory(String category);

    @Query(value = "SELECT * FROM question q where q.category=:category order by RAND() limit :noOfQuestions", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int noOfQuestions);
}
