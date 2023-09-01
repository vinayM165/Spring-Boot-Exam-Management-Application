package com.example.quizspringboot.Controller;

import com.example.quizspringboot.Controllers.QuizController;
import com.example.quizspringboot.Model.QuestionWrapper;
import com.example.quizspringboot.Model.Response;
import com.example.quizspringboot.Service.QuizService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestQuizController {

    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuizController quizController;

    @Test
    public void testCreateQuiz() {
        String title = "title";
        String category = "category";
        int qnum = 1;
        when(quizService.createQuiz(title, category, qnum)).thenReturn(new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED));

        ResponseEntity<String> response = quizController.create(title, category, qnum);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo("Quiz created successfully");
    }

    @Test
    public void testGetQuizQuestions() {
        int id = 1;
        List<QuestionWrapper> questions = List.of(new QuestionWrapper(), new QuestionWrapper());
        when(quizService.getQuizQuestions(id)).thenReturn(new ResponseEntity<>(questions, HttpStatus.OK));

        ResponseEntity<List<QuestionWrapper>> response = quizController.getQuizQuestions(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(questions);
    }

    @Test
    public void testGetAllQuizIdTitle() {
        List<Map<Integer, String>> quizzes = List.of(Map.of(1, "title1"), Map.of(2, "title2"));
        when(quizService.getAllQuizIdTitle()).thenReturn(new ResponseEntity<>(quizzes, HttpStatus.OK));

        ResponseEntity<List<Map<Integer, String>>> response = quizController.AllQuiz();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(quizzes);
    }

    @Test
    public void testCalculateResult() {
        int id = 1;
        List<Response> responses = List.of(new Response(), new Response());
        when(quizService.calculateResult(id, responses)).thenReturn(new ResponseEntity<>(1, HttpStatus.OK));

        ResponseEntity<Integer> response = quizController.getResult(id, responses);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(1);
    }

    @Test
    public void testDeleteQuiz() {
        int id = 1;
        when(quizService.deleteQuiz(id)).thenReturn(new ResponseEntity<>("Quiz deleted successfully", HttpStatus.OK));

        ResponseEntity<String> response = quizController.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Quiz deleted successfully");
    }
}
