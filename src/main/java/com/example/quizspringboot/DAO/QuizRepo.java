package com.example.quizspringboot.DAO;


import com.example.quizspringboot.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface QuizRepo extends JpaRepository<Quiz,Integer> {

    @Query(value = "select q.id,q.title from Quiz q",nativeQuery = true)
    List<Map<Integer, String>> getAllQuizIdTitle();
}
