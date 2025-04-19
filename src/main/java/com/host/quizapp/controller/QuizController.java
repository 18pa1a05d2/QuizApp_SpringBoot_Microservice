package com.host.quizapp.controller;

import com.host.quizapp.pojo.Question;
import com.host.quizapp.service.QuizService;
import com.host.quizapp.wrapper.QuestionWrapper;
import com.host.quizapp.wrapper.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/Quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping(path="/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam Integer noOfQuestions, @RequestParam String title){
        return quizService.createQuiz(category, noOfQuestions, title);
    }

    @GetMapping(path="/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping(path="/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }
}
