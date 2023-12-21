package com.springboot.quizservice.service;

import com.springboot.quizservice.dao.QuizDao;
import com.springboot.quizservice.feign.QuizInterface;
import com.springboot.quizservice.model.QuestionWrapper;
import com.springboot.quizservice.model.Quiz;
import com.springboot.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int num, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,num).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);

        return questions;
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        return quizInterface.getScore(responses);
    }
}
