package com.example.quizspringboot.Service;

import com.example.quizspringboot.DAO.QuestionRep;
import com.example.quizspringboot.Model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestQuestionService {
   @Autowired
    QuestionService questionService;

   @MockBean
    QuestionRep questionRepository;
    @Test
    public void testGetAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Python","Easy","option1","option2","option3","option4","What is python?",3));
        when(questionRepository.findAll()).thenReturn(questions);

        ResponseEntity<List<Question>> response = questionService.getAllQuestions();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetQuestionByCategory() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Python","Easy","option1","option2","option3","option4","What is python?",3));
        when(questionRepository.findQuestionByCategoryIgnoreCase("Python")).thenReturn(questions);

        ResponseEntity<List<Question>> response = questionService.getQuestionByCategory("Python");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetQuestionsByDifficulty() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Python","Easy","option1","option2","option3","option4","What is python?",3));
        when(questionRepository.findQuestionByDifficultyLevelIgnoreCase("Easy")).thenReturn(questions);

        ResponseEntity<List<Question>> response = questionService.getQuestionsByDifficulty("Easy");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetQuestionById() {
        Question question = new Question("Python","Easy","option1","option2","option3","option4","What is python?",3);
        when(questionRepository.findById(1)).thenReturn(Optional.of(question));

        ResponseEntity<Question> response = questionService.getQuestionById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Python", response.getBody().getCategory());
    }

    @Test
    public void testAddQuestion() {
        Question question = new Question("Python","Easy","option1","option2","option3","option4","What is python?",3);
        when(questionRepository.save(question)).thenReturn(question);

        ResponseEntity<Question> response = questionService.addQuestion(question);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Python", response.getBody().getCategory());
    }

    @Test
    public void testAddQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Python","Easy","option1","option2", "option3", "option4", "What is python?", 3));

        when(questionRepository.saveAll(questions)).thenReturn(new ArrayList<>());

        ResponseEntity<String> response = questionService.addQuestions(questions);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetRandomQuestionbyCategory() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Python", "Easy", "option1", "option2", "option3", "option4", "What is python?", 3));

        when(questionRepository.findRandomQuestionByCategory("Python", 1)).thenReturn(questions);

        ResponseEntity<List<Question>> response = questionService.getRandomQuestionbyCategory("Python", 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCategory() {
        List<String> categories = new ArrayList<>();
        categories.add("Python");

        when(questionRepository.getCategories()).thenReturn(categories);

        ResponseEntity<List<String>> response = questionService.getCategory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Python", response.getBody().get(0));
    }
}

