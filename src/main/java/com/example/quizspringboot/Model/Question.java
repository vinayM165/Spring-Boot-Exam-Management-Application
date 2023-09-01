package com.example.quizspringboot.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
    private String difficultyLevel;
    @Column(name = "option_1")
    private String option1;
    @Column(name = "option_2")
    private String option2;
    @Column(name = "option_3")
    private String option3;
    @Column(name = "option_4")
    private String option4;
    private String questionTitle;
    private Integer rightAnswer;

    public Question(String category, String difficultyLevel, String option1, String option2, String option3, String option4, String questionTitle, Integer rightAnswer) {
        this.category = category;
        this.difficultyLevel = difficultyLevel;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.questionTitle = questionTitle;
        this.rightAnswer = rightAnswer;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
