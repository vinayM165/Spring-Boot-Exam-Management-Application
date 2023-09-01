package com.example.quizspringboot.Controller;


import com.example.quizspringboot.Controllers.QuestionController;
import com.example.quizspringboot.Model.Question;
import com.example.quizspringboot.Service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestQuestionController {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionController questionController;

    @Test
    public void testGetAllQuestions() {
        List<Question> questions = List.of(new Question(), new Question());
        when(questionService.getAllQuestions()).thenReturn(new ResponseEntity<>(questions, HttpStatus.OK));

        ResponseEntity<List<Question>> response = questionController.getAllQuestions();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(questions);
    }

    @Test
    public void testGetQuestion() {
        int id = 1;
        Question question = new Question();
        when(questionService.getQuestionById(id)).thenReturn(new ResponseEntity<>(question, HttpStatus.OK));

        ResponseEntity<Question> response = questionController.getQuestion(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(question);
    }

    @Test
    public void testGetQuestionByCategory() {
        String category = "category";
        List<Question> questions = List.of(new Question(), new Question());
        when(questionService.getQuestionByCategory(category)).thenReturn(new ResponseEntity<>(questions, HttpStatus.OK));

        ResponseEntity<List<Question>> response = questionController.Category(category);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(questions);
    }

    @Test
    public void testGetCategory() {
        List<String> categories = List.of("category1", "category2");
        when(questionService.getCategory()).thenReturn(new ResponseEntity<>(categories, HttpStatus.OK));

        ResponseEntity<List<String>> response = questionController.Category();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(categories);
    }

    @Test
    public void testGetQuestionsByDifficulty() {
        String difficulty = "difficulty";
        List<Question> questions = List.of(new Question(), new Question());
        when(questionService.getQuestionsByDifficulty(difficulty)).thenReturn(new ResponseEntity<>(questions, HttpStatus.OK));

        ResponseEntity<List<Question>> response = questionController.Difficulty(difficulty);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(questions);
    }

    @Test
    public void testAddQuestion() {
        Question question = new Question();
        when(questionService.addQuestion(question)).thenReturn(new ResponseEntity<>(question, HttpStatus.CREATED));

        ResponseEntity<Question> response = questionController.addQuestion(question);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(question);
    }

    @Test
    public void testAddQuestions() {
        List<Question> questions = List.of(new Question(), new Question());
        when(questionService.addQuestions(questions)).thenReturn(new ResponseEntity<>("Questions added successfully", HttpStatus.CREATED));

        ResponseEntity<String> response = questionController.addQuestions(questions);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo("Questions added successfully");
    }

    @Test
    public void testGetRandomQuestionbyCategory() {
        int qnum = 1;
        String category = "category";
        List<Question> questions = List.of(new Question());
        when(questionService.getRandomQuestionbyCategory(category, qnum)).thenReturn(new ResponseEntity<>(questions, HttpStatus.OK));

        ResponseEntity<List<Question>> response = questionController.randomQuestion(qnum, category);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(questions);
    }
}
