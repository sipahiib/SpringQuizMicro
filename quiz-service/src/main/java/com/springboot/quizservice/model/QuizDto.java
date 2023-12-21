package com.springboot.quizservice.model;

import lombok.Data;

@Data
public class QuizDto {
    String categoryName;
    int numQuestions;
    String title;
}
