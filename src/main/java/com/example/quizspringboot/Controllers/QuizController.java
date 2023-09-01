package com.example.quizspringboot.Controllers;

import com.example.quizspringboot.Model.QuestionWrapper;
import com.example.quizspringboot.Model.Response;
import com.example.quizspringboot.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @GetMapping("/create")
    public ResponseEntity<String> create(@RequestParam("title") String title,@RequestParam("category") String category, @RequestParam("qnum")  int Qnum){
            return quizService.createQuiz(title,category,Qnum);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
       return quizService.getQuizQuestions(id);
    }

    @GetMapping("")
    public ResponseEntity<List<Map<Integer, String>>> AllQuiz(){
        return quizService.getAllQuizIdTitle();
    }

    @PostMapping("/result/{id}")
    public ResponseEntity<Integer> getResult(@PathVariable int id,@RequestBody List<Response> response){
        return quizService.calculateResult(id,response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return quizService.deleteQuiz(id);
    }
}
