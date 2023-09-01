package com.example.quizspringboot.repository;

import com.example.quizspringboot.DAO.QuestionRep;
import com.example.quizspringboot.DAO.QuizRepo;
import com.example.quizspringboot.Model.Question;
import com.example.quizspringboot.Model.Quiz;
import org.assertj.core.api.Assert;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.*;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestQuizRepo {

    public final Logger logger = Logger.getLogger(TestQuizRepo.class.getName());
    @Autowired
    QuizRepo repo;

    @Autowired
    QuestionRep rep;

    @Autowired
    private TestEntityManager entityManager;
    @Test
    void quizRepo_createQuiz(){
        Question question = new Question("Python","Easy","option1","option2","option3","option4","What is python?",3);
        question = entityManager.persistAndFlush(question);
        Quiz quiz = new Quiz("Test Quiz", List.of(question));
        Quiz savedQuiz = repo.save(quiz);
        int id = savedQuiz.getId();
        assertThat(id).isNotNull();
        assertThat(savedQuiz).isNotNull();
        assertThat(quiz.getTitle()).isEqualTo("Test Quiz");
        logger.info("vinay"+quiz.getQuestions().getClass().toString());
        logger.info("vinay"+List.of(question).toString());
        assertThat(new ArrayList<>(quiz.getQuestions())).isEqualTo(List.of(question));
    }

    @Test
    void quizRepo_updateQuiz(){
        Question question = new Question("Python","Easy","option1","option2","option3","option4","What is python?",3);
        question = entityManager.persistAndFlush(question);
        Quiz quiz = new Quiz("Test Quiz", new ArrayList<>(Arrays.asList(question)));
        Quiz savedQuiz = repo.save(quiz);
        int id = savedQuiz.getId();

        Quiz foundQuiz = repo.findById(id).orElse(null);
        foundQuiz.setTitle("Quiz Test");
        Quiz fQuiz = repo.save(foundQuiz);

        assertThat(fQuiz.getId()).isNotNull();
        assertThat(fQuiz.getTitle()).isEqualTo("Quiz Test");
    }
    @Test
    void quizRepo_deleteQuiz(){
        Question question = new Question("Python","Easy","option1","option2","option3","option4","What is python?",3);
        question = entityManager.persistAndFlush(question);
        Quiz quiz = new Quiz("Test Quiz", new ArrayList<>(Arrays.asList(question)));
        Quiz savedQuiz = repo.save(quiz);
        int id = savedQuiz.getId();
        repo.deleteById(id);
        Quiz resultQuiz = repo.findById(id).orElse(null);
        assertThat(resultQuiz).isNull();
    }
}
