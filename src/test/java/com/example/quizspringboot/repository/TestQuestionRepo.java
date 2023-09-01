package com.example.quizspringboot.repository;

import com.example.quizspringboot.DAO.QuestionRep;
import com.example.quizspringboot.Model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestQuestionRepo {

    @Autowired
    QuestionRep repo;
    @Test
    void questionRepo_saveQuestion(){
        Question question = new Question(1,"Python","Easy","option1","option2","option3","option4","What is python?",3);
        Question savedQuestion = repo.save(question);
        int id = savedQuestion.getId();

        assertThat(savedQuestion).isNotNull();
        assertThat(savedQuestion.getId()).isNotNull();
        assertThat(savedQuestion.getCategory()).isEqualTo("Python");
        assertThat(savedQuestion.getDifficultyLevel()).isEqualTo("Easy");
        assertThat(savedQuestion.getQuestionTitle()).isEqualTo("What is python?");
        assertThat(savedQuestion.getRightAnswer()).isEqualTo(3);
    }

    @Test
    void questionRepo_updateQuestion(){
        Question question = new Question(1,"Python","Easy","option1","option2","option3","option4","What is python?",3);
        Question savedQuestion = repo.save(question);
        int id = savedQuestion.getId();

        Question foundQuestion = repo.findById(id).orElse(null);
        assert foundQuestion != null;
        foundQuestion.setDifficultyLevel("Medium");
        Question found2Question = repo.save(foundQuestion);

        assertThat(found2Question).isNotNull();
        assertThat(found2Question.getId()).isNotNull();
        assertThat(found2Question.getCategory()).isEqualTo("Python");
        assertThat(found2Question.getDifficultyLevel()).isEqualTo("Medium");
        assertThat(found2Question.getQuestionTitle()).isEqualTo("What is python?");
        assertThat(found2Question.getRightAnswer()).isEqualTo(3);
    }

    @Test
    void questionRepo_deleteQuestion(){
        Question question = new Question(1,"Python","Easy","option1","option2","option3","option4","What is python?",3);
        Question savedQuestion = repo.save(question);
        int id = savedQuestion.getId();
        repo.deleteById(id);
        Question deletedQuestion = repo.findById(id).orElse(null);
        assertThat(deletedQuestion).isNull();
    }


}
