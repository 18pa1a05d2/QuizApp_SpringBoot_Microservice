package com.host.quizapp.controller;

import com.host.quizapp.pojo.Question;
import com.host.quizapp.service.QuestionService;
import com.host.quizapp.utils.QuestionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping(path="/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getAllQuestion();
    }

    @GetMapping(path="/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category ){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping(path="/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
}
