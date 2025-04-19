package com.host.quizapp.service;

import com.host.quizapp.constants.CommonConstants;
import com.host.quizapp.pojo.Question;
import com.host.quizapp.pojo.Quiz;
import com.host.quizapp.repo.QuestionDao;
import com.host.quizapp.repo.QuizDao;
import com.host.quizapp.utils.QuestionUtils;
import com.host.quizapp.wrapper.QuestionWrapper;
import com.host.quizapp.wrapper.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, Integer noOfQuestions, String title) {
        try {
            List<Question> questions = questionDao.findRandomQuestionByCategory(category, noOfQuestions);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);
            return QuestionUtils.getResponseEntity(CommonConstants.QUIZ_CREATED, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return QuestionUtils.getResponseEntity(CommonConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        try {
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> questionFromDb = quiz.get().getQuestions();
            List<QuestionWrapper> questionForUser = new ArrayList<>();
            for(Question q : questionFromDb){
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionForUser.add(qw);
            }
            return new ResponseEntity<>(questionForUser, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        try {
            Quiz quiz = quizDao.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            int score = 0;
            int i = 0;
            for (Response response : responses) {
                if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
                    score++;
                }
                i++;
            }
            return new ResponseEntity<>(score, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
