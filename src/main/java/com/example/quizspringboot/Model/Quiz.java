package com.example.quizspringboot.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    public Quiz(String title, List<Question> questions) {
        this.title = title;
        this.questions = questions;
    }

    @ManyToMany
    private List<Question> questions;
}
