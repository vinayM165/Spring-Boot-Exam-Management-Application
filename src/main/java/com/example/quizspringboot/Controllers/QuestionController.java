package com.example.quizspringboot.Controllers;

import com.example.quizspringboot.Model.Question;
import com.example.quizspringboot.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    public QuestionService qService;

    @GetMapping()
    public ResponseEntity<List<Question>> getAllQuestions(){
        return qService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable int id){
        return qService.getQuestionById(id);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> Category(@PathVariable String category){
        return qService.getQuestionByCategory(category);
    }
    @GetMapping("/category")
    public ResponseEntity<List<String>> Category(){
        return qService.getCategory();
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Question>> Difficulty(@PathVariable String difficulty){
        return qService.getQuestionsByDifficulty(difficulty);
    }

    @PostMapping()
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return qService.addQuestion(question);
    }
    @PostMapping("/addList")
    public ResponseEntity<String> addQuestions(@RequestBody List<Question> question){
        return qService.addQuestions(question);
    }

    @GetMapping("/random")
    public ResponseEntity<List<Question>> randomQuestion(@RequestParam int qnum, @RequestParam String category){
        return qService.getRandomQuestionbyCategory(category,qnum);
    }
}
