package com.example.quizspringboot.Service;

import com.example.quizspringboot.DAO.QuizRepo;
import com.example.quizspringboot.Model.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log
public class QuizService {
    @Autowired
    QuestionService questionService;

    @Autowired
    QuizRepo repo;

    public ResponseEntity<List<Map<Integer, String>>> getAllQuizIdTitle(){
        try{
            return new ResponseEntity<>(repo.getAllQuizIdTitle(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = repo.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getCategory(),q.getDifficultyLevel(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getQuestionTitle());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }
    public ResponseEntity<String> createQuiz(String title,String category, int qnum) {
        try {
            List<Question> qlist = questionService.getRandomQuestionbyCategory(category, qnum).getBody();
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(qlist);
            repo.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        int right = 0;
        try {
            Quiz quiz = repo.findById(id).get();
            List<Question> questions = quiz.getQuestions();

            int i = 0;
            for (Response response : responses) {
                if (response.getResponse().equals(questions.get(i).getRightAnswer()))
                    right++;
                i++;
            }
        }catch (Exception e){
            return new ResponseEntity<>(right, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(0, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteQuiz(int id){
        try {
            repo.deleteById(id);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failure",HttpStatus.BAD_REQUEST);
    }

}
