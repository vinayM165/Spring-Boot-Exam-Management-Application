package com.example.quizspringboot.DAO;

import com.example.quizspringboot.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRep extends JpaRepository<Question, Integer> {

     List<Question> findQuestionByCategoryIgnoreCase(String category);

     List<Question> findQuestionByDifficultyLevelIgnoreCase(String difficulty);

     @Query(value = "SELECT * FROM question WHERE LOWER(category) = LOWER(:category) ORDER BY RANDOM() LIMIT :qnum",nativeQuery = true)
     List<Question> findRandomQuestionByCategory(String category, int qnum);

     @Query(value = "select category from Question",nativeQuery = true)
     List<String> getCategories();
}
