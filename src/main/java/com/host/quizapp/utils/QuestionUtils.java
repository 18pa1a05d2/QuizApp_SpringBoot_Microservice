package com.host.quizapp.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class QuestionUtils {

    private QuestionUtils(){}

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus status){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", status);
    }
}
