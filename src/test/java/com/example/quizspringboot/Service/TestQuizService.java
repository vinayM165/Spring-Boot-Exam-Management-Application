package com.example.quizspringboot.Service;
import com.example.quizspringboot.DAO.QuestionRep;
import com.example.quizspringboot.DAO.QuizRepo;
import com.example.quizspringboot.Model.*;

import com.example.quizspringboot.DAO.QuestionRep;
import com.example.quizspringboot.Model.Question;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestQuizService {

    @Autowired
    private QuizService quizService;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuizRepo quizRepo;

    @Test
    public void testGetAllQuizIdTitle() {
        List<Map<Integer, String>> quizIdTitles = new ArrayList<>();
        Map<Integer, String> quizIdTitle = new HashMap<>();
        quizIdTitle.put(1, "Test Quiz");
        quizIdTitles.add(quizIdTitle);
        when(quizRepo.getAllQuizIdTitle()).thenReturn(quizIdTitles);

        ResponseEntity<List<Map<Integer, String>>> response = quizService.getAllQuizIdTitle();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetQuizQuestions() {
        Quiz quiz = new Quiz();
        quiz.setId(1);
        quiz.setTitle("Test Quiz");
        Question question = new Question("Python","Easy","option1","option2","option3","option4","What is python?",3);
        question.setId(1);
        quiz.setQuestions(Arrays.asList(question));
        when(quizRepo.findById(1)).thenReturn(Optional.of(quiz));

        ResponseEntity<List<QuestionWrapper>> response = quizService.getQuizQuestions(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testCreateQuiz() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Python","Easy","option1","option2","option3","option4","What is python?",3));
        when(questionService.getRandomQuestionbyCategory("Python", 10)).thenReturn(new ResponseEntity<>(questions, HttpStatus.OK));

        ResponseEntity<String> response = quizService.createQuiz("Test Quiz", "Python", 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCalculateResult() {
        Quiz quiz = new Quiz();
        quiz.setId(1);
        quiz.setTitle("Test Quiz");
        Question question = new Question("Python","Easy","option1","option2","option3","option4","What is python?",3);
        question.setId(1);
        quiz.setQuestions(Arrays.asList(question));
        when(quizRepo.findById(1)).thenReturn(Optional.of(quiz));

        List<Response> responses = new ArrayList<>();
        responses.add(new Response(1, "3"));

        ResponseEntity<Integer> response = quizService.calculateResult(1, responses);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteQuiz() {
        doNothing().when(quizRepo).deleteById(1);

        ResponseEntity<String> response = quizService.deleteQuiz(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());

    }
}
