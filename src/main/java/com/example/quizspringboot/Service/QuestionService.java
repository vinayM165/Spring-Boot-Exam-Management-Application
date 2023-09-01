package com.example.quizspringboot.Service;

import com.example.quizspringboot.DAO.QuestionRep;
import com.example.quizspringboot.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRep repo;
    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String Category) {
        try {
            return new ResponseEntity<>(repo.findQuestionByCategoryIgnoreCase(Category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByDifficulty(String Difficulty){
        try {
            return new ResponseEntity<>(repo.findQuestionByDifficultyLevelIgnoreCase(Difficulty),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Question> getQuestionById(int id) {
        try {
            return new ResponseEntity<>(repo.findById(id).orElse(null),HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> addQuestion(Question q){
        try {
            return new ResponseEntity<>(repo.save(q), HttpStatus.CREATED);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> addQuestions(List<Question> q){
        try {
            repo.saveAll(q);
            return new ResponseEntity<>( "Success", HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( "Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getRandomQuestionbyCategory(String category, int qnum) {
        try {
            return new ResponseEntity<>(repo.findRandomQuestionByCategory(category, qnum), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


    public ResponseEntity<List<String>> getCategory() {
        return new ResponseEntity<>(repo.getCategories(),HttpStatus.OK);
    }
}
