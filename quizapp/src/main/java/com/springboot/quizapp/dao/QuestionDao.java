package com.springboot.quizapp.dao;

import com.springboot.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT  * FROM question q Where q.category=:category order by random() LIMIT :num",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int num);
}
